package com.beckytidus.waifumod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.beckytidus.waifumod.registry.EntityRegistry;
import com.beckytidus.waifumod.registry.ItemRegistry;
import com.beckytidus.waifumod.command.WaifuCommand;
import com.beckytidus.waifumod.interaction.EntityInteractionHandler;
import com.beckytidus.waifumod.interaction.ItemUseHandler;
import com.beckytidus.waifumod.event.EntityAttributeHandler;
import com.beckytidus.waifumod.dialogue.DelayedChatReply;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class WaifuMod implements ModInitializer {
	public static final String MOD_ID = "waifu-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Waifu Mod...");

		EntityRegistry.register();
		ItemRegistry.register();
		EntityAttributeHandler.register();
		WaifuCommand.register();
		EntityInteractionHandler.register();
		ItemUseHandler.register();
		ServerTickEvents.END_SERVER_TICK.register(DelayedChatReply::tick);

		RegistryKey<ItemGroup> ingredientsKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.ofVanilla("ingredients"));
		ItemGroupEvents.modifyEntriesEvent(ingredientsKey).register(entries -> entries.add(ItemRegistry.WAIFU_SUMMONER));

		RegistryKey<ItemGroup> foodKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.ofVanilla("food_and_drinks"));
		ItemGroupEvents.modifyEntriesEvent(foodKey).register(entries -> {
			entries.add(ItemRegistry.ONIGIRI);
			entries.add(ItemRegistry.POCKY);
			entries.add(ItemRegistry.RAMEN);
			entries.add(ItemRegistry.BENTO);
			entries.add(ItemRegistry.TAIYAKI);
			entries.add(ItemRegistry.MOCHI);
		});

		LOGGER.info("Waifu Mod loaded successfully!");
	}
}