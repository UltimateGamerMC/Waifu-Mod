package com.beckytidus.waifumod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import com.beckytidus.waifumod.entity.WaifuEntity;
import com.beckytidus.waifumod.registry.EntityRegistry;
import com.beckytidus.waifumod.registry.ItemRegistry;
import com.beckytidus.waifumod.registry.WaifuNames;
import com.beckytidus.waifumod.registry.WaifuPersonalities;
import com.beckytidus.waifumod.registry.WaifuSkins;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class WaifuCommand {
    private static final double FIND_RANGE = 128.0;

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerWaifuCommand(dispatcher);
        });
    }

    @Nullable
    private static WaifuEntity getClosestWaifu(ServerCommandSource source) {
        PlayerEntity player = source.getPlayer();
        if (player == null) return null;
        ServerWorld world = source.getWorld();
        WaifuEntity closest = null;
        double closestSq = FIND_RANGE * FIND_RANGE;
        for (WaifuEntity gf : world.getEntitiesByClass(WaifuEntity.class, player.getBoundingBox().expand(FIND_RANGE), g -> g.getOwner() == player)) {
            double dSq = gf.squaredDistanceTo(player);
            if (dSq < closestSq) {
                closestSq = dSq;
                closest = gf;
            }
        }
        return closest;
    }

    private static void registerWaifuCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("waifu")
                .then(CommandManager.literal("summon")
                    .executes(context -> {
                        PlayerEntity player = context.getSource().getPlayerOrThrow();
                        ServerWorld world = context.getSource().getWorld();
                        WaifuEntity waifu = new WaifuEntity(EntityRegistry.WAIFU, world);
                        waifu.setPosition(player.getX(), player.getY(), player.getZ());
                        waifu.setOwner(player);
                        waifu.setPlayerCustomName(WaifuNames.pickRandom(world.getRandom()));
                        waifu.setTextureVariant(WaifuSkins.pickRandomTextureVariant(world.getRandom()));
                        waifu.setPersonality(WaifuPersonalities.random(world.getRandom()));
                        world.spawnEntity(waifu);
                        context.getSource().sendMessage(Text.literal("♥ A waifu has appeared for " + player.getName().getString() + ", senpai!"));
                        return 1;
                    })
                )
                .then(CommandManager.literal("relationship")
                    .then(CommandManager.argument("level", IntegerArgumentType.integer(0, 100))
                        .executes(context -> {
                            WaifuEntity gf = getClosestWaifu(context.getSource());
                            if (gf == null) {
                                context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                                return 0;
                            }
                            int level = IntegerArgumentType.getInteger(context, "level");
                            gf.setRelationshipLevel(level);
                            context.getSource().sendMessage(Text.literal("♥ Set relationship to " + level));
                            return 1;
                        })
                    )
                )
                .then(CommandManager.literal("list")
                    .executes(context -> {
                        PlayerEntity player = context.getSource().getPlayerOrThrow();
                        ServerWorld world = context.getSource().getWorld();
                        var list = world.getEntitiesByClass(WaifuEntity.class, player.getBoundingBox().expand(FIND_RANGE), g -> g.getOwner() == player);
                        context.getSource().sendMessage(Text.literal("Waifus nearby: " + list.size()));
                        for (WaifuEntity gf : list) {
                            context.getSource().sendMessage(gf.getStatsDisplayText());
                        }
                        return 1;
                    })
                )
                .then(CommandManager.literal("give")
                    .executes(context -> {
                        return giveSummoner(context.getSource(), context.getSource().getPlayerOrThrow());
                    })
                )
                .then(CommandManager.literal("mood")
                    .then(CommandManager.argument("level", IntegerArgumentType.integer(0, 100))
                        .executes(context -> {
                            WaifuEntity gf = getClosestWaifu(context.getSource());
                            if (gf == null) {
                                context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                                return 0;
                            }
                            int level = IntegerArgumentType.getInteger(context, "level");
                            gf.setMoodLevel(level);
                            context.getSource().sendMessage(Text.literal("♥ Set mood to " + level));
                            return 1;
                        })
                    )
                )
                .then(CommandManager.literal("texture")
                    .then(CommandManager.argument("variant", StringArgumentType.string())
                        .suggests((context, builder) -> {
                            for (int i = 1; i <= 20; i++) builder.suggest(String.valueOf(i));
                            builder.suggest("default");
                            builder.suggest("alt");
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            WaifuEntity gf = getClosestWaifu(context.getSource());
                            if (gf == null) {
                                context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                                return 0;
                            }
                            String v = StringArgumentType.getString(context, "variant");
                            gf.setTextureVariant(v);
                            context.getSource().sendMessage(Text.literal("♥ Set texture to " + v));
                            return 1;
                        })
                    )
                )
                .then(CommandManager.literal("skin")
                    .then(CommandManager.argument("username", StringArgumentType.string())
                        .executes(context -> {
                            WaifuEntity gf = getClosestWaifu(context.getSource());
                            if (gf == null) {
                                context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                                return 0;
                            }
                            String username = StringArgumentType.getString(context, "username");
                            gf.setSkinOwnerName(username);
                            context.getSource().sendMessage(Text.literal("♥ Set waifu skin to player: " + username));
                            return 1;
                        })
                )
                )
                .then(CommandManager.literal("stats")
                    .executes(context -> {
                        WaifuEntity gf = getClosestWaifu(context.getSource());
                        if (gf == null) {
                            context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                            return 0;
                        }
                        context.getSource().sendMessage(gf.getStatsDisplayText());
                        context.getSource().getPlayerOrThrow().sendMessage(gf.getStatsDisplayText(), true);
                        return 1;
                    })
                )
                .then(CommandManager.literal("personality")
                    .then(CommandManager.argument("type", StringArgumentType.word())
                        .suggests((context, builder) -> {
                            for (WaifuPersonalities p : WaifuPersonalities.values()) builder.suggest(p.id);
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            WaifuEntity gf = getClosestWaifu(context.getSource());
                            if (gf == null) {
                                context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                                return 0;
                            }
                            String type = StringArgumentType.getString(context, "type");
                            WaifuPersonalities p = WaifuPersonalities.byId(type);
                            if (p == null) {
                                context.getSource().sendMessage(Text.literal("Unknown personality. Options: " + WaifuPersonalities.idList()));
                                return 0;
                            }
                            gf.setPersonality(p);
                            context.getSource().sendMessage(Text.literal("♥ Personality set to " + p.displayName));
                            return 1;
                        })
                    )
                )
                .then(CommandManager.literal("outfit")
                    .then(CommandManager.argument("name", StringArgumentType.word())
                        .suggests((context, builder) -> {
                            builder.suggest("default");
                            builder.suggest("school");
                            builder.suggest("maid");
                            builder.suggest("kimono");
                            builder.suggest("magical");
                            builder.suggest("nurse");
                            builder.suggest("catgirl");
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            WaifuEntity gf = getClosestWaifu(context.getSource());
                            if (gf == null) {
                                context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                                return 0;
                            }
                            String name = StringArgumentType.getString(context, "name");
                            gf.setOutfit(name);
                            context.getSource().sendMessage(Text.literal("♥ Outfit set to " + name));
                            return 1;
                        })
                    )
                )
                .then(CommandManager.literal("transform")
                    .executes(context -> {
                        WaifuEntity gf = getClosestWaifu(context.getSource());
                        if (gf == null) {
                            context.getSource().sendMessage(Text.literal("No waifu found nearby"));
                            return 0;
                        }
                        if (gf.startTransform()) {
                            context.getSource().sendMessage(Text.literal("✦ Magical transformation! ✦"));
                            return 1;
                        }
                        context.getSource().sendMessage(Text.literal("Cannot transform yet (needs relationship 25+ and off cooldown)"));
                        return 0;
                    })
                )
        );
    }

    private static int giveSummoner(ServerCommandSource source, PlayerEntity target) {
        ItemStack stack = new ItemStack(ItemRegistry.WAIFU_SUMMONER);
        if (!target.getInventory().insertStack(stack)) {
            target.dropItem(stack, false);
        }
        source.sendMessage(Text.literal("Gave Waifu Summoner to " + target.getName().getString()));
        return 1;
    }
}
