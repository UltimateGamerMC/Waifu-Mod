package com.beckytidus.waifumod.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import com.beckytidus.waifumod.WaifuMod;
import com.beckytidus.waifumod.entity.WaifuEntity;

public class EntityRegistry {
    public static EntityType<WaifuEntity> WAIFU;

    public static void register() {
        Identifier id = Identifier.of(WaifuMod.MOD_ID, "waifu");
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
        
        WAIFU = Registry.register(
                Registries.ENTITY_TYPE,
                id,
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WaifuEntity::new)
                        .dimensions(EntityDimensions.changing(0.9f, 1.9f))
                        .build(key)
        );
    }
}