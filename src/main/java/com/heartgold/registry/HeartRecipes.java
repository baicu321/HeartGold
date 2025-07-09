package com.heartgold.registry;

import com.google.gson.JsonObject;
import com.heartgold.HeartGold;
import com.heartgold.init.recipe.ShapedRecipe;
import com.heartgold.init.recipe.ShapelessRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HeartRecipes {

    public static void init() {
        register("heartcrafting_shaped", ShapedRecipe.Type.INSTANCE);
        register("heartcrafting_shaped", ShapedRecipe.Serializer.INSTANCE);
        register("heartcrafting_shapeless", ShapelessRecipe.Type.INSTANCE);
        register("heartcrafting_shapeless", ShapelessRecipe.Serializer.INSTANCE);
    }

    public static RecipeSerializer<?> register(String id, RecipeSerializer<?> recipeType) {
        return Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(HeartGold.MOD_ID, id), recipeType);
    }

    public static <T extends Recipe<?>> RecipeType<T> register(String id, RecipeType<T> recipeType) {
        return Registry.register(Registries.RECIPE_TYPE, new Identifier(HeartGold.MOD_ID, id), recipeType);
    }

}
