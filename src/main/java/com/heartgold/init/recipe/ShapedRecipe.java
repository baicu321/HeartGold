package com.heartgold.init.recipe;

import com.google.gson.JsonObject;
import com.heartgold.util.RecipeUtil;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义有序合成配方实现类（支持5x5网格）
 */
public record ShapedRecipe(
        Identifier id,          // 配方唯一标识符
        ItemStack output,       // 合成输出物品
        List<List<Ingredient>> recipeItems // 5x5配方材料矩阵（行优先存储）
) implements Recipe<SimpleInventory> {

    /**
     * 检查两个二维列表的维度是否不同
     * @param first 第一个二维列表
     * @param second 第二个二维列表
     * @return 如果行列数不同返回true
     */
    private static <T, M> boolean differentSize(List<List<T>> first, List<List<M>> second) {
        return first.size() != second.size() || first.get(0).size() != second.get(0).size();
    }

    /**
     * 匹配物品矩阵与配方要求（独立方法）
     * @param inventory 5x5物品矩阵
     * @return 是否匹配配方
     */
    public boolean matches(List<List<ItemStack>> inventory) {
        if (differentSize(inventory, this.recipeItems)) return false;
        // 逐行逐列比对材料
        for (int i = 0; i < this.recipeItems.size(); i++) {
            List<Ingredient> ing = this.recipeItems.get(i);  // 配方要求
            List<ItemStack> inv = inventory.get(i);          // 实际输入
            for (int j = 0; j < ing.size(); j++)
                if (!ing.get(j).test(inv.get(j)))  // 测试物品是否匹配
                    return false;
        }
        return true;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        // 将物品栏转换为5x5矩阵
        List<List<ItemStack>> stacks = RecipeUtil.toTable(inventory.stacks, 5, 5);
        return matches(stacks);  // 复用匹配逻辑
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return null;  // 实际合成逻辑应在OutputSlot中实现
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 5 && height >= 5;  // 需要至少5x5的工作区
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.output.copy();  // 返回输出物品的副本
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;  // 返回关联的序列化器
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;  // 返回关联的配方类型
    }

    /**
     * 配方类型声明
     */
    public enum Type implements RecipeType<ShapedRecipe> {
        INSTANCE  // 单例模式实现
    }

    /**
     * 配方序列化器（处理JSON/网络数据包转换）
     */
    public enum Serializer implements RecipeSerializer<ShapedRecipe> {
        INSTANCE;  // 单例模式实现

        /**
         * 从JSON解析配方
         */
        @Override
        public ShapedRecipe read(Identifier id, JsonObject json) {
            // 解析输出物品
            ItemStack output = net.minecraft.recipe.ShapedRecipe.outputFromJson(
                    JsonHelper.getObject(json, "result"));

            // 解析符号映射表（如"A": "minecraft:diamond"）
            Map<String, Ingredient> map = RecipeUtil.readSymbols(
                    JsonHelper.getObject(json, "key"));

            // 获取5x5图案（自动校验尺寸）
            String[] strings = RecipeUtil.getPattern(
                    JsonHelper.getArray(json, "pattern"), 5, 5);

            // 将图案转换为材料矩阵
            Ingredient[][] inputs = RecipeUtil.replacePattern(strings, map);
            return new ShapedRecipe(id, output, RecipeUtil.toTable(inputs));
        }

        /**
         * 从网络数据包解析配方
         */
        @Override
        public ShapedRecipe read(Identifier id, PacketByteBuf buf) {
            // 读取矩阵尺寸
            int width = buf.readInt();
            int height = buf.readInt();

            // 读取材料矩阵
            List<List<Ingredient>> inputs = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                List<Ingredient> ingredients = new ArrayList<>();
                for (int j = 0; j < height; j++)
                    ingredients.add(Ingredient.fromPacket(buf));  // 逐个材料反序列化
                inputs.add(ingredients);
            }

            // 读取输出物品
            ItemStack output = ItemStack.fromNbt(buf.readNbt());
            return new ShapedRecipe(id, output, inputs);
        }

        /**
         * 将配方写入网络数据包
         */
        @Override
        public void write(PacketByteBuf buf, ShapedRecipe recipe) {
            // 写入矩阵尺寸
            buf.writeInt(recipe.recipeItems.size());
            buf.writeInt(recipe.recipeItems.get(0).size());

            // 写入所有材料
            for (List<Ingredient> recipeItem : recipe.recipeItems)
                for (Ingredient ingredient : recipeItem)
                    ingredient.write(buf);  // 逐个材料序列化

            // 写入输出物品
            NbtCompound compound = new NbtCompound();
            recipe.output.writeNbt(compound);
            buf.writeNbt(compound);
        }
    }
}