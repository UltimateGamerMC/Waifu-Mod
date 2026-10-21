package com.beckytidus.waifumod.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class WaifuEntityRenderState extends PlayerEntityRenderState {
    @Nullable
    public Text statsLabel;
}
