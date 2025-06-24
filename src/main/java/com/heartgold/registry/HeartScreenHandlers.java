package com.heartgold.registry;

import com.heartgold.HeartGold;
import com.heartgold.client.screen.HeartCraftingScreen;
import com.heartgold.screen.HeartCraftingScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class HeartScreenHandlers {
    public static final ScreenHandlerType<HeartCraftingScreenHandler> HEART_CRAFTING_SCREEN_HANDLER = register(new Identifier(HeartGold.MOD_ID, "extreme_crafting_table"), HeartCraftingScreenHandler::new);

    public static <T extends ScreenHandler> ScreenHandlerType<T> register(Identifier id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }

    public static void init(){//For server init
    }

    public static void registerAllScreenHandlers() {
        HandledScreens.register(HeartScreenHandlers.HEART_CRAFTING_SCREEN_HANDLER, HeartCraftingScreen::new);
    }
}
