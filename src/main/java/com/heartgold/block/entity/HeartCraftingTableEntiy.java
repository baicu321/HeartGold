package com.heartgold.block.entity;

import com.heartgold.block.ModBlocks;
import com.heartgold.init.recipe.ShapedRecipe;
import com.heartgold.init.recipe.ShapelessRecipe;
import com.heartgold.registry.HeartBlockEntitiles;
import com.heartgold.screen.HeartCraftingScreenHandler;
import com.heartgold.util.RecipeUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import java.util.List;

public class HeartCraftingTableEntiy extends BlockEntity implements NamedScreenHandlerFactory,ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(26, ItemStack.EMPTY);

    public HeartCraftingTableEntiy(BlockPos pos, BlockState state) {
        super(HeartBlockEntitiles.HEART_CRAFTING_TABLE_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, HeartCraftingTableEntiy entity) {
        // 只在服务端执行逻辑
        if (world.isClient) return;

        // 检查合成区是否有物品
        boolean hasItems = false;
        for (int i = 0; i < 25; i++) {
            if (!entity.inventory.get(i).isEmpty()) {
                hasItems = true;
                break;
            }
        }

        // 如果合成区为空且输出槽不为空，则清空输出槽
        if (!hasItems) {
            if (!entity.inventory.get(25).isEmpty()) {
                entity.inventory.set(25, ItemStack.EMPTY);
                entity.markDirty();
            }
            return;
        }

        ItemStack previous = entity.inventory.get(25).copy();
        ItemStack current = ItemStack.EMPTY;

        // 只在前25格(5x5)中查找配方
        List<List<ItemStack>> table = RecipeUtil.toTable(entity.inventory.subList(0, 25), 5, 5);

        // 检查有序配方
        for (ShapedRecipe recipe : world.getRecipeManager().listAllOfType(ShapedRecipe.Type.INSTANCE)) {
            if (recipe.matches(table)) {
                current = recipe.output().copy();
                break;
            }
        }

        // 检查无序配方
        if (current.isEmpty()) {
            for (ShapelessRecipe recipe : world.getRecipeManager().listAllOfType(ShapelessRecipe.Type.INSTANCE)) {
                if (recipe.matches(new SimpleInventory(entity.inventory.subList(0, 25).toArray(new ItemStack[0])), world)) {
                    current = recipe.getOutput(null).copy();
                    break;
                }
            }
        }

        // 只有当输出确实变化时才更新
        if (!ItemStack.areEqual(previous, current)) {
            entity.inventory.set(25, current);
            entity.markDirty();
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(this.getCachedState().getBlock().getTranslationKey());
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new HeartCraftingScreenHandler(syncId, inv, this);
    }
}