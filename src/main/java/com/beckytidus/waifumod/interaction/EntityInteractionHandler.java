package com.beckytidus.waifumod.interaction;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import com.beckytidus.waifumod.entity.WaifuEntity;
import com.beckytidus.waifumod.registry.ItemRegistry;

public class EntityInteractionHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof WaifuEntity gf) {
                return handleWaifuInteraction(player, gf, hand);
            }
            return ActionResult.PASS;
        });
    }

    public static ActionResult handleWaifuInteraction(PlayerEntity player, WaifuEntity waifu, Hand hand) {
        if (hand != Hand.MAIN_HAND) return ActionResult.PASS;
        if (!waifu.canInteract(player)) return ActionResult.PASS;
        boolean isOwner = waifu.getOwner() == player;
        if (player.getStackInHand(hand).isEmpty()) {
            if (player instanceof ServerPlayerEntity spe) {
                spe.sendMessage(waifu.getStatsDisplayText(), true);
            }
            if (player.isSneaking() && isOwner && !waifu.isAngeredAtOwner()) {
                waifu.headpat(player);
            } else {
                waifu.toggle();
            }
            waifu.setLastInteractionTick(player.getEntityWorld().getTime());
            return ActionResult.SUCCESS;
        }

        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.NETHER_STAR) && isOwner && !waifu.isAngeredAtOwner()) {
            if (waifu.startTransform()) {
                if (!player.isCreative()) stack.decrement(1);
                waifu.setLastInteractionTick(player.getEntityWorld().getTime());
                return ActionResult.SUCCESS;
            }
            player.sendMessage(Text.literal("♥ " + waifu.getDisplayNameForChat() + ": Not yet, senpai... we need to grow closer first."), false);
            return ActionResult.SUCCESS;
        }
        if (isFood(stack)) {
            waifu.feedEntity(stack);
            waifu.setLastInteractionTick(player.getEntityWorld().getTime());
            if (!player.isCreative()) stack.decrement(1);
            return ActionResult.SUCCESS;
        }
        if (isGift(stack)) {
            waifu.addAffection(5);
            waifu.setMoodLevel(Math.min(100, waifu.getMoodLevel() + 5));
            waifu.spawnHeartParticles();
            player.sendMessage(Text.literal("♥ " + waifu.getDisplayNameForChat() + ": " + getGiftResponse(waifu)), false);
            waifu.setLastInteractionTick(player.getEntityWorld().getTime());
            if (!player.isCreative()) stack.decrement(1);
            return ActionResult.SUCCESS;
        }
        if (stack.isOf(Items.NAME_TAG)) {
            String name = stack.getName().getString();
            if (name.isEmpty() || name.equals("Name Tag")) name = "Waifu";
            waifu.setPlayerCustomName(name);
            waifu.setLastInteractionTick(player.getEntityWorld().getTime());
            if (!player.isCreative()) stack.decrement(1);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private static String getGiftResponse(WaifuEntity waifu) {
        String[] responses = {
            "You remembered what I like! Thank you, senpai!",
            "For me? Kyaa~ you're too kind, senpai!",
            "I'll treasure this forever. Really!",
            "This is so thoughtful of you, senpai.",
            "You always know how to make me smile~",
            "A gift for me? I'm so happy, senpai!",
            "I'll keep this close to my heart, senpai."
        };
        return responses[waifu.getEntityWorld().getRandom().nextInt(responses.length)];
    }

    private static boolean isFood(ItemStack stack) {
        return stack.isOf(Items.APPLE) || stack.isOf(Items.GOLDEN_APPLE) ||
               stack.isOf(Items.WHEAT) || stack.isOf(Items.BREAD) ||
               stack.isOf(Items.CARROT) || stack.isOf(Items.POTATO) ||
               stack.isOf(Items.BAKED_POTATO) || stack.isOf(Items.PUMPKIN_PIE) ||
               stack.isOf(Items.CAKE) || stack.isOf(Items.HONEY_BOTTLE) ||
               stack.isOf(Items.MELON_SLICE) || stack.isOf(Items.BEEF) ||
               stack.isOf(Items.COOKED_BEEF) || stack.isOf(Items.PORKCHOP) ||
               stack.isOf(Items.COOKED_PORKCHOP) || stack.isOf(Items.CHICKEN) ||
               stack.isOf(Items.COOKED_CHICKEN) ||
               stack.isOf(ItemRegistry.ONIGIRI) || stack.isOf(ItemRegistry.POCKY) ||
               stack.isOf(ItemRegistry.RAMEN) || stack.isOf(ItemRegistry.BENTO) ||
               stack.isOf(ItemRegistry.TAIYAKI) || stack.isOf(ItemRegistry.MOCHI);
    }

    private static boolean isGift(ItemStack stack) {
        return stack.isOf(Items.POPPY) || stack.isOf(Items.DANDELION) || stack.isOf(Items.BLUE_ORCHID) ||
               stack.isOf(Items.ALLIUM) || stack.isOf(Items.AZURE_BLUET) || stack.isOf(Items.RED_TULIP) ||
               stack.isOf(Items.ORANGE_TULIP) || stack.isOf(Items.PINK_TULIP) || stack.isOf(Items.WHITE_TULIP) ||
               stack.isOf(Items.LILAC) || stack.isOf(Items.ROSE_BUSH) || stack.isOf(Items.PEONY) ||
               stack.isOf(Items.SUNFLOWER) || stack.isOf(Items.CORNFLOWER) || stack.isOf(Items.LILY_OF_THE_VALLEY) ||
               stack.isOf(Items.TORCHFLOWER) || stack.isOf(Items.PITCHER_PLANT) || stack.isOf(Items.EMERALD) ||
               stack.isOf(Items.DIAMOND) || stack.isOf(Items.AMETHYST_SHARD);
    }
}
