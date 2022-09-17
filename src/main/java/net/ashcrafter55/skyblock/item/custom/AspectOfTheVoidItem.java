package net.ashcrafter55.skyblock.item.custom;

import net.ashcrafter55.skyblock.mana.PlayerManaProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AspectOfTheVoidItem extends SwordItem {
    private int manaCost = 45;

    public AspectOfTheVoidItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
            if(mana.getMana() < manaCost){
                player.displayClientMessage(Component.literal("NOT ENOUGH MANA")
                        .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
            } else {
                BlockHitResult ray = GetRayTrace(level, player, ClipContext.Fluid.NONE);
                BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
                player.moveTo(lookPos.getX() + .5, lookPos.getY(), lookPos.getZ() + .5);
                player.fallDistance = 0f;
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0));
                level.playLocalSound(lookPos.getX(), lookPos.getY(), lookPos.getZ(), SoundEvents.ENDERMAN_TELEPORT,
                        SoundSource.MASTER, 1.0F, 1.0F, false);
                mana.subMana(manaCost);
            }
        });

        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Ability: Instant Transmission")
                .withStyle(ChatFormatting.GOLD));
        components.add(Component.literal("RIGHT CLICK")
                .withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD));
        components.add(Component.literal("Teleport 12 blocks ahead of")
                .withStyle(ChatFormatting.GRAY));
        components.add(Component.literal("you and gain +50 Speed")
                .withStyle(ChatFormatting.GRAY));
        components.add(Component.literal("for 3 seconds")
                .withStyle(ChatFormatting.GRAY));
        components.add(Component.literal("Mana Cost: " + manaCost)
                .withStyle(ChatFormatting.DARK_GRAY));

        super.appendHoverText(stack, level, components, flag);
    }

    protected static BlockHitResult GetRayTrace(Level level, LivingEntity player, ClipContext.Fluid fluid) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition(1.0f);
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 8;
        Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, fluid, player));
    }
}
