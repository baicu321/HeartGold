package com.heartgold.entity.client;

import com.heartgold.HeartGold;
import com.heartgold.entity.custom.GoldPigEntity;
import com.heartgold.entity.custom.SandHandEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SandHandRenderer extends MobEntityRenderer<SandHandEntity,SandHandModel<SandHandEntity>> {
    private static final Identifier TEXTURE = new Identifier(HeartGold.MOD_ID,"textures/entity/sand_hand.png");
    public SandHandRenderer(EntityRendererFactory.Context context) {
        super(context,new SandHandModel<>(context.getPart(ModModelLayers.SAND_HAND)),0.5f);
    }

    @Override
    public Identifier getTexture(SandHandEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(SandHandEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
