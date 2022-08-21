package net.ashcrafter55.skyblock.item.custom;

import net.ashcrafter55.skyblock.mana.PlayerManaProvider;
import net.ashcrafter55.skyblock.networking.ModMessages;
import net.ashcrafter55.skyblock.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RogueSwordItem extends SwordItem {
    private int manaCost = 50;

    public RogueSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
            if(mana.getMana() < manaCost){
                player.displayClientMessage(Component.literal("NOT ENOUGH MANA")
                        .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1));
                player.getCooldowns().addCooldown(this, 600);
                mana.subMana(manaCost);
                player.sendSystemMessage(Component.literal("Current Mana: " + mana.getMana()));
            }
        });

        return super.use(level, player, hand);
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Ability: Speed Boost")
                .withStyle(ChatFormatting.GOLD));
        components.add(Component.literal("RIGHT CLICK")
                .withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD));
        components.add(Component.literal("Grants +100 speed for 30s")
                .withStyle(ChatFormatting.GRAY));;
        components.add(Component.literal("Mana Cost: " + manaCost)
                .withStyle(ChatFormatting.DARK_GRAY));

        super.appendHoverText(stack, level, components, flag);
    }
}
