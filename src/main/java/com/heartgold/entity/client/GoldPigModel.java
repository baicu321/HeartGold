// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.heartgold.entity.client;

import com.heartgold.entity.animation.GoldPigAnimation;
import com.heartgold.entity.custom.GoldPigEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class GoldPigModel<T extends GoldPigEntity> extends SinglePartEntityModel<T> {
	private final ModelPart goldpig;
	private final ModelPart head;

    public GoldPigModel(ModelPart root) {
        this.goldpig = root.getChild("goldpig");
		this.head = goldpig.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("goldpig", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(16, 16).cuboid(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, -7.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -11.0F, 9.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(28, 32).cuboid(-5.0F, -18.0F, -5.0F, 10.0F, 16.0F, 8.0F, new Dilation(0.5F))
		.uv(28, 8).cuboid(-5.0F, -18.0F, -5.0F, 10.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData leg0 = root.addChild("leg0", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -6.0F, 6.0F));

		ModelPartData leg1 = root.addChild("leg1", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.0F, -6.0F, 6.0F));

		ModelPartData leg3 = root.addChild("leg3", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.0F, -6.0F, -6.0F));

		ModelPartData leg2 = root.addChild("leg2", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -6.0F, -6.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(GoldPigEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw,headPitch);

		this.animateMovement(GoldPigAnimation.WALK,limbSwing,limbSwingAmount,2f,2.5f);
	}

	private void setHeadAngles(float headAngles, float headPitch) {
		headAngles = MathHelper.clamp(headAngles,-30F,30F);
		headPitch = MathHelper.clamp(headPitch,-25.0F,45.0F);
		this.head.yaw = headAngles * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		goldpig.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return this.goldpig;
	}
}