// BackgroundRendererMixin.java
package com.heartgold.mixin;

import com.heartgold.config.GoldWaterConfig;
import com.heartgold.fluid.ModFluids;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

    // 使用 TAIL 注入确保最后执行
    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void overrideGoldWaterFog(
            Camera camera,
            BackgroundRenderer.FogType fogType,
            float viewDistance,
            boolean thickFog,
            float tickDelta,
            CallbackInfo ci) {

        // 检查是否在金水中
        if (isInGoldWater(camera)) {
            // 仅针对金水应用金色雾效
            applyGoldWaterFog();
        }
    }

    // 检测玩家是否在金水中
    private static boolean isInGoldWater(Camera camera) {
        // 1. 检查摄像机所在位置的流体
        BlockPos cameraPos = BlockPos.ofFloored(camera.getPos());
        World world = camera.getFocusedEntity().getWorld();

        if (world == null) return false;

        // 2. 获取流体状态
        FluidState fluidState = world.getFluidState(cameraPos);

        // 3. 检查是否为金水
        return fluidState.isIn(FluidTags.WATER) && // 确保是水类流体
                (fluidState.isOf(ModFluids.STILL_GOLD_WATER) ||
                        fluidState.isOf(ModFluids.FLOWING_GOLD_WATER));
    }

    // 应用金水雾效
    private static void applyGoldWaterFog() {
        // 金色雾效 (RGB: 0.98f, 0.85f, 0.1f)
        RenderSystem.setShaderFogColor(0.98f, 0.85f, 0.1f);

        // 可选：调整雾效范围
        RenderSystem.setShaderFogStart(0.5f);
        RenderSystem.setShaderFogEnd(15.0f);
    }
}