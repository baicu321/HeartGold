package com.heartgold.entity.client;

import com.heartgold.HeartGold;
import com.heartgold.entity.custom.GoldPigEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GoldPigRenderer extends MobEntityRenderer<GoldPigEntity,GoldPigModel<GoldPigEntity>> {
    private static final Identifier TEXTURE = new Identifier(HeartGold.MOD_ID,"textures/entity/goldpig.png");
    public GoldPigRenderer(EntityRendererFactory.Context context) {
        super(context,new GoldPigModel<>(context.getPart(ModModelLayers.GOLDPIG)),0.5f);
    }

    @Override
    public Identifier getTexture(GoldPigEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(GoldPigEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()){
            matrixStack.scale(0.7f,0.7f,0.7f);
        }else {
            matrixStack.scale(1.0f,1.0f,1.0f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
