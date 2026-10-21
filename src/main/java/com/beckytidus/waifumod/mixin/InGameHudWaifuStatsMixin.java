package com.beckytidus.waifumod.mixin;

import com.beckytidus.waifumod.entity.WaifuEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudWaifuStatsMixin {
    private static final int WAIFU_LOOK_RANGE = 10;

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At("TAIL"))
    private void waifuMod$renderWaifuStats(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Entity lookedAt = client.targetedEntity;
        if (!(lookedAt instanceof WaifuEntity) && client.getCameraEntity() != null) {
            lookedAt = DebugRenderer.getTargetedEntity(client.getCameraEntity(), WAIFU_LOOK_RANGE).orElse(null);
        }
        if (lookedAt instanceof WaifuEntity gf && gf.isAlive()) {
            int w = context.getScaledWindowWidth();
            int h = context.getScaledWindowHeight();
            int y = h / 2 - 60;
            String stats = gf.getStatsDisplayText().getString();
            context.drawTextWithShadow(client.textRenderer, stats, (w - client.textRenderer.getWidth(stats)) / 2, y, 0xFFFFFF);
            String name = gf.getPlayerCustomName().isEmpty() ? "Waifu" : gf.getPlayerCustomName();
            context.drawTextWithShadow(client.textRenderer, name, (w - client.textRenderer.getWidth(name)) / 2, y - 10, 0xFFAAFF);
        }
    }
}
