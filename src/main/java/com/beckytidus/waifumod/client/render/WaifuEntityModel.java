package com.beckytidus.waifumod.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;

@Environment(EnvType.CLIENT)
public class WaifuEntityModel extends BipedEntityModel<WaifuEntityRenderState> {
    private final PlayerEntityModel inner;

    public WaifuEntityModel(ModelPart root, boolean thinArms) {
        super(root);
        this.inner = new PlayerEntityModel(root, thinArms);
    }

    @Override
    public void setAngles(WaifuEntityRenderState state) {
        inner.setAngles(state);
    }
}
