package com.heartgold.item;

import com.heartgold.HeartGold;
import com.heartgold.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> HEARTGOLDGROUP = register("heartgold_group");

    private static RegistryKey<ItemGroup> register(String id){
        return RegistryKey.of(RegistryKeys.ITEM_GROUP,new Identifier(HeartGold.MOD_ID,id));
    }

    public static void registGroups(){
        Registry.register(
                Registries.ITEM_GROUP,
                HEARTGOLDGROUP,
                ItemGroup.create(null,-1)
                        .displayName(Text.translatable("itemGroup.heartgold_group"))
                        .icon(()-> new ItemStack(ModItems.HEART_GOLD))

                        .entries(((displayContext, entries) -> {
                            entries.add(ModItems.HEART_GOLD );
                            entries.add(ModBlocks.HEARTGOLD_BLOCK);
                            entries.add(ModBlocks.HEART_PORTAL_BLOCK);
                            entries.add(ModItems.RAW_HEARTGOLD);
                            entries.add(ModBlocks.HEARTGOLD_ORE);
                            entries.add(ModBlocks.OVERFLOW_GOLD_ORE);
                            entries.add(ModBlocks.OVERFLOW_DEEPSLATE_GOLD_ORE);
                            entries.add(ModBlocks.HEART_CRAFTING_TABLE);
                        })).build()
                );

    }
}
