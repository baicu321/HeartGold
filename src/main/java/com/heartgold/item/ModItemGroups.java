package com.heartgold.item;

import com.heartgold.HeartGold;
import com.heartgold.block.ModBlocks;
import com.heartgold.fluid.ModFluids;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> HEARTGOLDGROUP = register("heartgold_group");//物品
    public static final RegistryKey<ItemGroup> HEARTGOLDGROUP1 = register("heartgold_group1");//生物刷怪蛋

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
                            entries.add(ModFluids.GOLD_WATER_BUCKET);
                            entries.add(ModItems.HEARTGOLD_SWORD);
                            entries.add(ModItems.HEARTGOLD_AXE);
                            entries.add(ModItems.HEARTGOLD_PICKAXE);
                            entries.add(ModItems.HEARTGOLD_SHOVEL);
                            entries.add(ModItems.HEARTGOLD_HOE);
                            entries.add(ModItems.FLINT_AND_GOLD);
                            entries.add(ModItems.GOLDPORKCHOP);
                        })).build()
                );

                Registry.register(
                        Registries.ITEM_GROUP,
                        HEARTGOLDGROUP1,
                        ItemGroup.create(null,-1)
                                .displayName(Text.translatable("itemGroup.heartgold_group1"))
                                .icon(()->new ItemStack(ModItems.GOLDPIG_SPAWN_EGG))

                                .entries(((displayContext, entries) -> {
                                   entries.add(ModItems.GOLDPIG_SPAWN_EGG);

                                })).build()
                                );

    }
}
