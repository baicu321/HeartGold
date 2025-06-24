package com.heartgold.registry;

import com.heartgold.HeartGold;
import com.heartgold.block.ModBlocks;
import com.heartgold.block.entity.HeartCraftingTableEntiy;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HeartBlockEntitiles {
    public static final BlockEntityType<HeartCraftingTableEntiy> HEART_CRAFTING_TABLE_ENTITY = register("heart_crafting_table_entity", FabricBlockEntityTypeBuilder.create(HeartCraftingTableEntiy::new, ModBlocks.HEART_CRAFTING_TABLE).build());
    private static <T extends BlockEntity> BlockEntityType<T> register(String name,BlockEntityType<T> type){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE,new Identifier(HeartGold.MOD_ID,name),type);
    }
    public static void init() {
    }

}

