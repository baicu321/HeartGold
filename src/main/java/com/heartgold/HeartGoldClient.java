package com.heartgold;

import com.heartgold.block.ModBlocks;
import com.heartgold.client.screen.HeartCraftingScreen;
import com.heartgold.registry.HeartResourceManagers;
import com.heartgold.registry.HeartScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class HeartGoldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HeartScreenHandlers.registerAllScreenHandlers();
        HeartResourceManagers.registerClient();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HEART_CRAFTING_TABLE, RenderLayer.getCutout());
//        ScreenRegistry.register(HeartGold.HEART_CRAFTING_HANDLER, HeartCraftingScreen::new);
        HandledScreens.register(HeartGold.HEART_CRAFTING_HANDLER, HeartCraftingScreen::new);
//        HandledScreens.register(
//                HeartGold.HEART_CRAFTING_HANDLER,
//                HeartCraftingScreen::new
//        );
        // 注册屏幕与ScreenHandler的关联
    }
}