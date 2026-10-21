package com.beckytidus.waifumod.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.beckytidus.waifumod.entity.WaifuEntity;
import com.beckytidus.waifumod.registry.EntityRegistry;
import com.beckytidus.waifumod.registry.WaifuNames;
import com.beckytidus.waifumod.registry.WaifuPersonalities;
import com.beckytidus.waifumod.registry.WaifuSkins;

import java.util.function.Consumer;

public class WaifuSummonerItem extends Item {
    public WaifuSummonerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (EntityRegistry.WAIFU != null) {
                WaifuEntity waifu = new WaifuEntity(EntityRegistry.WAIFU, world);
                waifu.setPosition(user.getX(), user.getY(), user.getZ());
                waifu.setOwner(user);
                waifu.setPlayerCustomName(WaifuNames.pickRandom(world.getRandom()));
                waifu.setTextureVariant(WaifuSkins.pickRandomTextureVariant(world.getRandom()));
                waifu.setPersonality(WaifuPersonalities.random(world.getRandom()));
                world.spawnEntity(waifu);

                user.sendMessage(Text.literal("♥ Your waifu has appeared, senpai! Kyaa~"), false);

                if (!user.isCreative()) {
                    user.getStackInHand(hand).decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.literal("Summons your anime waifu companion (random name + one of 13 skins). Stats: Lv, ♥Mood, Affection, Hunger, HP"));
    }
}
