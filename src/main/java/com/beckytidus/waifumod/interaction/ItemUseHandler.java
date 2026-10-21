package com.beckytidus.waifumod.interaction;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.beckytidus.waifumod.entity.WaifuEntity;
import com.beckytidus.waifumod.registry.EntityRegistry;
import com.beckytidus.waifumod.registry.ItemRegistry;
import com.beckytidus.waifumod.registry.WaifuNames;
import com.beckytidus.waifumod.registry.WaifuPersonalities;
import com.beckytidus.waifumod.registry.WaifuSkins;

public class ItemUseHandler {
    public static void register() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack stack = player.getStackInHand(hand);

            if (stack.getItem() == ItemRegistry.WAIFU_SUMMONER && !world.isClient()) {
                WaifuEntity waifu = new WaifuEntity(EntityRegistry.WAIFU, world);
                waifu.setPosition(player.getX(), player.getY(), player.getZ());
                waifu.setOwner(player);
                waifu.setPlayerCustomName(WaifuNames.pickRandom(world.getRandom()));
                waifu.setTextureVariant(WaifuSkins.pickRandomTextureVariant(world.getRandom()));
                waifu.setPersonality(WaifuPersonalities.random(world.getRandom()));
                world.spawnEntity(waifu);

                player.sendMessage(Text.literal("♥ Your waifu has appeared, senpai!"), false);

                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }
}
