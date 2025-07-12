package com.heartgold;

import com.heartgold.block.ModBlocks;
import com.heartgold.client.screen.HeartCraftingScreen;
import com.heartgold.entity.ModEntities;
import com.heartgold.entity.client.*;
import com.heartgold.fluid.ModFluids;
import com.heartgold.registry.HeartResourceManagers;
import com.heartgold.registry.HeartScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.registry.Registry;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

@Environment(EnvType.CLIENT)
public class HeartGoldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GOLDPIG, GoldPigModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GOLDPIG, GoldPigRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SAND_HAND, SandHandModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SAND_HAND,SandHandRenderer::new);
        HeartScreenHandlers.registerAllScreenHandlers();
        HeartResourceManagers.registerClient();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HEART_CRAFTING_TABLE, RenderLayer.getCutout());
//        ScreenRegistry.register(HeartGold.HEART_CRAFTING_HANDLER, HeartCraftingScreen::new);
        HandledScreens.register(HeartGold.HEART_CRAFTING_HANDLER, HeartCraftingScreen::new);
        ParticleFactoryRegistry.getInstance().register(HeartGold.DX_PARTICLE, FlameParticle.Factory::new);
//        HandledScreens.register(
//                HeartGold.HEART_CRAFTING_HANDLER,
//                HeartCraftingScreen::new
//        );
        // 注册屏幕与ScreenHandler的关联


        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GOLD_WATER,ModFluids.FLOWING_GOLD_WATER,
                new SimpleFluidRenderHandler(
                        new Identifier("heart-gold:block/gold_water_still"),
                        new Identifier("heart-gold:block/gold_water_flow")
                )

                );
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_GOLD_WATER, ModFluids.FLOWING_GOLD_WATER);
    }
}