package com.heartgold.data;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.heartgold.HeartGold;
import com.heartgold.data.singularity.Singularity;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import com.heartgold.init.recipe.ShapelessRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class DynamicManager {
    public static void process(DynamicData data) {
        if (!data.available()) return;
        if (data.recipe() != null)
            data.recipe().addToRecipes();
        if (data.singularity() != null)
            data.singularity().pushData();
    }

    public record DynamicData(List<String> dependency, DynamicRecipe recipe, SingularityPlus singularity) {
        public boolean available() {
            return new DependencyHolder(this.dependency).anyLoaded();
        }
    }

    public record DynamicRecipe(List<DynamicIngredient> catalyst, List<DynamicIngredient> stew,
                                List<DynamicIngredient> meatballs) {
        public void addToRecipes() {
            if (this.catalyst != null)
                ShapelessRecipe.findAndAdd(new Identifier(HeartGold.MOD_ID, "infinity_catalyst"), toIngredientList(this.catalyst));
            if (this.stew != null)
                ShapelessRecipe.findAndAdd(new Identifier(HeartGold.MOD_ID, "ultimate_stew"), toIngredientList(this.stew));
            if (this.meatballs != null)
                ShapelessRecipe.findAndAdd(new Identifier(HeartGold.MOD_ID, "cosmic_meatballs"), toIngredientList(this.meatballs));
        }
    }

    public record DynamicIngredient(String item, String tag) {
        public Ingredient toIngredient() {
            if (this.item != null && this.tag != null) {
                throw new JsonParseException("An ingredient entry is either a tag or an item, not both");
            } else if (this.item != null) {
                Item item = Registries.ITEM.getOrEmpty(Identifier.tryParse(this.item)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + this.item + "'"));
                return Ingredient.ofItems(item);
            } else if (this.tag != null) {
                Identifier identifier = new Identifier(this.tag);
                TagKey<Item> tagKey = TagKey.of(RegistryKeys.ITEM, identifier);
                return Ingredient.fromTag(tagKey);
            } else {
                throw new JsonParseException("An ingredient entry needs either a tag or an item");
            }
        }
    }

    public record SingularityPlus(int add, int mul) {
        public void pushData() {
            Singularity.pushData(this.add, this.mul);
        }
    }

    private static List<Ingredient> toIngredientList(List<DynamicIngredient> items) {
        return items.stream().map(DynamicIngredient::toIngredient).toList();
    }
}