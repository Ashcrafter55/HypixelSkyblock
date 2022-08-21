package net.ashcrafter55.skyblock.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ashcrafter55.skyblock.Skyblock;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ManaBarOverlay {
    private static final ResourceLocation MANA_BAR = new ResourceLocation(Skyblock.MOD_ID,
            "textures/mana/mana_bar.png");
    private static final ResourceLocation MANA_BAR_BACKGROUND = new ResourceLocation(Skyblock.MOD_ID,
            "textures/mana/mana_bar_background.png");
    private static final ResourceLocation MANA_INCREMENT = new ResourceLocation(Skyblock.MOD_ID,
            "textures/mana/mana_increment.png");

    public static final IGuiOverlay HUD_MANA_BAR = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        RenderSystem.setShaderTexture(0, MANA_BAR_BACKGROUND);
        GuiComponent.blit(poseStack, x + 10, y - 39, 0, 0, 81, 9, 81, 9);

        RenderSystem.setShaderTexture(0, MANA_INCREMENT);
        for(int i = 0; i < 80; i++) {
            if(ClientManaData.getPlayerMana() * 79 / 100 > i) {
                GuiComponent.blit(poseStack, x + 11 + i, y - 39, 0, 0, 1, 9, 1, 9);
            }
        }

        RenderSystem.setShaderTexture(0, MANA_BAR);
        GuiComponent.blit(poseStack, x + 10, y - 39, 0, 0, 81, 9, 81, 9);
    });
}
