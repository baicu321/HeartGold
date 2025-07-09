package com.heartgold.conpact.emi;

import com.heartgold.HeartGold;
import com.heartgold.block.ModBlocks;
import com.heartgold.data.singularity.SingularityHelper;
import com.heartgold.init.recipe.ShapedRecipe;
import com.heartgold.init.recipe.ShapelessRecipe;
import com.heartgold.item.ModItems;
import com.heartgold.registry.HeartScreenHandlers;
import com.heartgold.screen.HeartCraftingScreenHandler;
import com.heartgold.util.RecipeUtil;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.Comparison;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HeartCraftingRecipePlugin implements EmiPlugin {

    private static final Identifier HEART_CRAFTING = new Identifier(HeartGold.MOD_ID, "heart_crafting");
    private static final EmiTexture TEXTURE = new EmiTexture(new Identifier(HeartGold.MOD_ID, "textures/gui/jei/jei_heart_crafting.png"), 0, 0, 166, 90);
    private static final EmiStack WORKSTATION = EmiStack.of(ModBlocks.HEART_CRAFTING_TABLE);
    private static final EmiRecipeCategory HEART_CRAFTING_CATEGORY = new EmiRecipeCategory(HEART_CRAFTING, WORKSTATION);
    @Override
    public void register(EmiRegistry registry) {
            registry.addCategory(HEART_CRAFTING_CATEGORY);
            registry.addWorkstation(HEART_CRAFTING_CATEGORY,WORKSTATION);
            for (ShapedRecipe recipe : registry.getRecipeManager().listAllOfType(ShapedRecipe.Type.INSTANCE))
                registry.addRecipe(new EmiHeartCraftingRecipe(recipe));
        for (ShapelessRecipe recipe : registry.getRecipeManager().listAllOfType(ShapelessRecipe.Type.INSTANCE))
                registry.addRecipe(new EmiHeartCraftingRecipe(recipe));
            registry.addRecipeHandler(HeartScreenHandlers.HEART_CRAFTING_SCREEN_HANDLER,new HeartCraftingHandler());
            registry.setDefaultComparison(ModItems.SINGULARITY, Comparison.of((a,b)-> SingularityHelper.compare(a.getItemStack(),b.getItemStack())));
    }

    public record EmiHeartCraftingRecipe(Identifier id, boolean shapeless, List<List<Ingredient>> inputs,
                                         ItemStack output) implements EmiRecipe{
            public EmiHeartCraftingRecipe(ShapedRecipe recipe){
                this(recipe.id(),false,recipe.recipeItems(),recipe.output().copy());
            }

            public EmiHeartCraftingRecipe(ShapelessRecipe recipe){
                this(recipe.getId(),true, RecipeUtil.toTable(recipe.getAllIngredients(),5,5),recipe.getOutput(null));
            }
            @Override
            public EmiRecipeCategory getCategory(){
                return HEART_CRAFTING_CATEGORY;
            }
            @Override
            public @Nullable Identifier getId(){
                return this.id;
            }
            @Override
            public List<EmiIngredient> getInputs(){
                List<EmiIngredient> ingredients = new ArrayList<>();
                for (List<Ingredient> ingredient : this.inputs)
                    for (Ingredient i : ingredient)
                        ingredients.add(EmiIngredient.of(i));
                return ingredients;
            }
        @Override
        public List<EmiStack> getOutputs() {
            return List.of(EmiStack.of(this.output));
        }


        @Override
        public int getDisplayWidth() {
            return 168;
        }

        @Override
        public int getDisplayHeight() {
            return 92;
        }

        @Override
        public void addWidgets(WidgetHolder widgets) {
            widgets.addTexture(TEXTURE, 0, 0);
            if (this.shapeless)
                widgets.addTexture(EmiTexture.SHAPELESS, 149, 2);
            for (int i = 0; i < this.inputs.size(); i++) {
                List<Ingredient> ingredients = this.inputs.get(i);
                for (int j = 0; j < ingredients.size(); j++)
                    widgets.addSlot(EmiIngredient.of(ingredients.get(j)), j * 18 + 1, i * 18 + 1);
            }
            widgets.addSlot(this.getOutputs().get(0), 137, 50).large(true).recipeContext(this);
        }
    }
}
