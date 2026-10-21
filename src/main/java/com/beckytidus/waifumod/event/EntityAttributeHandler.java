package com.beckytidus.waifumod.event;

import com.beckytidus.waifumod.entity.WaifuEntity;
import com.beckytidus.waifumod.registry.EntityRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class EntityAttributeHandler {
    public static void register() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.WAIFU, WaifuEntity.createWaifuAttributes());
    }
}