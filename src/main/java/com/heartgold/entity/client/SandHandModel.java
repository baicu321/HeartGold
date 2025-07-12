// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.heartgold.entity.client;

import com.heartgold.entity.custom.SandHandEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class SandHandModel <T extends SandHandEntity>extends SinglePartEntityModel<T> {
	private final ModelPart sandhand;
//	private final ModelPart palm1;
//	private final ModelPart palm2;
//	private final ModelPart thumb;
//	private final ModelPart index1;
//	private final ModelPart index2;
//	private final ModelPart middle1;
//	private final ModelPart middle2;
//	private final ModelPart ring1;
//	private final ModelPart ring2;
//	private final ModelPart little1;
//	private final ModelPart little2;
//	private final ModelPart eye;
	public SandHandModel(ModelPart root) {
		this.sandhand = root.getChild("sandhand");
//		this.palm1 = root.getChild("palm1");
//		this.palm2 = root.getChild("palm2");
//		this.thumb = root.getChild("thumb");
//		this.index1 = root.getChild("index1");
//		this.index2 = root.getChild("index2");
//		this.middle1 = root.getChild("middle1");
//		this.middle2 = root.getChild("middle2");
//		this.ring1 = root.getChild("ring1");
//		this.ring2 = root.getChild("ring2");
//		this.little1 = root.getChild("little1");
//		this.little2 = root.getChild("little2");
//		this.eye = root.getChild("eye");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData sandhand = modelPartData.addChild("sandhand", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData palm1 = sandhand.addChild("palm1", ModelPartBuilder.create(), ModelTransform.of(0.0F, -36.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		ModelPartData cube_r1 = palm1.addChild("cube_r1", ModelPartBuilder.create().uv(406, 376).cuboid(-134.0F, -39.0F, -20.0F, 150.0F, 100.0F, 53.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 3.0F, -0.9F, 0.0F, -0.3927F, 0.0F));

		ModelPartData cube_r2 = palm1.addChild("cube_r2", ModelPartBuilder.create().uv(0, 376).cuboid(-14.0F, -37.0F, -18.0F, 150.0F, 100.0F, 53.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -2.0F, 0.0F, 0.3927F, 0.0F));

		ModelPartData palm2 = sandhand.addChild("palm2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -149.0F, 0.0F));

		ModelPartData cube_r3 = palm2.addChild("cube_r3", ModelPartBuilder.create().uv(0, 188).cuboid(-134.0F, -69.0F, -25.0F, 150.0F, 130.0F, 58.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -11.0F, -0.9F, 0.0F, -0.3927F, 0.0F));

		ModelPartData cube_r4 = palm2.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -69.0F, -23.0F, 150.0F, 130.0F, 58.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, -2.0F, 0.0F, 0.3927F, 0.0F));

		ModelPartData thumb = sandhand.addChild("thumb", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		ModelPartData cube_r5 = thumb.addChild("cube_r5", ModelPartBuilder.create().uv(400, 699).cuboid(-71.0F, -136.0F, -58.0F, 51.0F, 80.0F, 45.0F, new Dilation(0.0F)), ModelTransform.of(-135.0F, -153.0F, -2.0F, 0.0F, -0.733F, 0.0F));

		ModelPartData cube_r6 = thumb.addChild("cube_r6", ModelPartBuilder.create().uv(626, 160).cuboid(-78.0F, -100.0F, -86.0F, 64.0F, 100.0F, 45.0F, new Dilation(0.0F)), ModelTransform.of(-145.0F, -112.0F, 26.0F, 0.0F, -0.6109F, 0.0F));

		ModelPartData index1 = sandhand.addChild("index1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -51.0F, -2.0F));

		ModelPartData cube_r7 = index1.addChild("cube_r7", ModelPartBuilder.create().uv(416, 160).cuboid(-24.0F, -120.0F, -49.0F, 55.0F, 120.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(-111.0F, -178.0F, -4.0F, 0.0F, -0.3054F, 0.0F));

		ModelPartData index2 = sandhand.addChild("index2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -51.0F, -2.0F));

		ModelPartData cube_r8 = index2.addChild("cube_r8", ModelPartBuilder.create().uv(630, 689).cuboid(-32.0F, -115.0F, -23.0F, 50.0F, 105.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(-94.0F, -288.0F, -27.0F, 0.0F, -0.3054F, 0.0F));

		ModelPartData middle1 = sandhand.addChild("middle1", ModelPartBuilder.create(), ModelTransform.of(48.0F, -51.0F, 23.0F, 0.0F, 0.0087F, 0.0F));

		ModelPartData cube_r9 = middle1.addChild("cube_r9", ModelPartBuilder.create().uv(0, 529).cuboid(-24.0F, -120.0F, -49.0F, 55.0F, 120.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(-89.0F, -178.0F, -7.0F, 0.0F, -0.0873F, 0.0F));

		ModelPartData middle2 = sandhand.addChild("middle2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -51.0F, 3.0F));

		ModelPartData cube_r10 = middle2.addChild("cube_r10", ModelPartBuilder.create().uv(630, 529).cuboid(-32.0F, -120.0F, -23.0F, 50.0F, 110.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(-30.0F, -288.0F, -10.0F, 0.0F, -0.0873F, 0.0F));

		ModelPartData ring1 = sandhand.addChild("ring1", ModelPartBuilder.create(), ModelTransform.of(6.0F, -51.0F, 8.0F, 0.0F, 0.1571F, 0.0F));

		ModelPartData cube_r11 = ring1.addChild("cube_r11", ModelPartBuilder.create().uv(210, 529).cuboid(-24.0F, -120.0F, -49.0F, 55.0F, 120.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(28.0F, -178.0F, 10.0F, 0.0F, 0.0873F, 0.0F));

		ModelPartData ring2 = sandhand.addChild("ring2", ModelPartBuilder.create(), ModelTransform.of(56.0F, -51.0F, 26.0F, 0.0089F, 0.1745F, 0.0015F));

		ModelPartData cube_r12 = ring2.addChild("cube_r12", ModelPartBuilder.create().uv(0, 699).cuboid(-26.0452F, -134.9992F, -28.8314F, 50.0F, 105.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(-18.0F, -268.0F, -25.0F, 0.0F, 0.0436F, 0.0F));

		ModelPartData little1 = sandhand.addChild("little1", ModelPartBuilder.create(), ModelTransform.of(62.0F, -51.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData cube_r13 = little1.addChild("cube_r13", ModelPartBuilder.create().uv(420, 529).cuboid(-24.0F, -120.0F, -49.0F, 55.0F, 120.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(43.0F, -178.0F, 5.0F, 0.0F, 0.1309F, 0.0F));

		ModelPartData little2 = sandhand.addChild("little2", ModelPartBuilder.create(), ModelTransform.of(59.0F, -51.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData cube_r14 = little2.addChild("cube_r14", ModelPartBuilder.create().uv(200, 699).cuboid(-26.0F, -130.0F, -23.0F, 50.0F, 100.0F, 50.0F, new Dilation(0.0F)), ModelTransform.of(47.0F, -268.0F, -18.0F, 0.0F, 0.1222F, 0.0F));

		ModelPartData eye = sandhand.addChild("eye", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r15 = eye.addChild("cube_r15", ModelPartBuilder.create().uv(416, 0).cuboid(-34.0F, -41.0F, -57.0F, 80.0F, 80.0F, 80.0F, new Dilation(0.0F)), ModelTransform.of(-5.8F, -161.0F, -21.0F, 1.5708F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 1024, 1024);
	}
	@Override
	public void setAngles(SandHandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		sandhand.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return this.sandhand;
	}
}