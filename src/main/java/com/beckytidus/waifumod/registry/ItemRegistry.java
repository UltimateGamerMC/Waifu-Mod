package com.beckytidus.waifumod.registry;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import com.beckytidus.waifumod.WaifuMod;
import com.beckytidus.waifumod.item.WaifuSummonerItem;

public class ItemRegistry {
    public static Item WAIFU_SUMMONER;
    public static Item ONIGIRI;
    public static Item POCKY;
    public static Item RAMEN;
    public static Item BENTO;
    public static Item TAIYAKI;
    public static Item MOCHI;

    public static void register() {
        Identifier id = Identifier.of(WaifuMod.MOD_ID, "waifu_summoner");
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item.Settings settings = new Item.Settings()
                .maxCount(1)
                .registryKey(key);

        WAIFU_SUMMONER = Registry.register(
            Registries.ITEM,
            key,
            new WaifuSummonerItem(settings)
        );

        ONIGIRI = registerFood("onigiri", 6, 0.6f);
        POCKY = registerFood("pocky", 3, 0.4f);
        RAMEN = registerFood("ramen", 9, 0.8f);
        BENTO = registerFood("bento", 11, 1.0f);
        TAIYAKI = registerFood("taiyaki", 5, 0.6f);
        MOCHI = registerFood("mochi", 4, 0.5f);
    }

    private static Item registerFood(String name, int nutrition, float saturation) {
        Identifier id = Identifier.of(WaifuMod.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = new Item.Settings()
                .maxCount(16)
                .food(new FoodComponent.Builder().nutrition(nutrition).saturationModifier(saturation).build())
                .registryKey(key);
        return Registry.register(Registries.ITEM, key, new Item(settings));
    }
}
