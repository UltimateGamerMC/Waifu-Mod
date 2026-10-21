package com.beckytidus.waifumod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import com.beckytidus.waifumod.registry.EntityRegistry;
import com.beckytidus.waifumod.client.render.WaifuEntityRenderer;

public class WaifuModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.WAIFU, WaifuEntityRenderer::new);
    }
}