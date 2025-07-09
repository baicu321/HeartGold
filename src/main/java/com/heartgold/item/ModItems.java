package com.heartgold.item;

import com.heartgold.HeartGold;
import com.heartgold.block.ModBlocks;
import com.heartgold.entity.ModEntities;
import com.heartgold.entity.custom.GoldPigEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public class ModItems {
  //  public static final Item AIR = register(Blocks.AIR, new AirBlockItem(Blocks.AIR, new Item.Settings()));
    public static final Item HEART_GOLD = registerItems("heartgold",new Item(new Item.Settings().fireproof()){//哈尔特黄金
        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("tooltip.heartgold.heart_gold"));
            super.appendTooltip(stack, world, tooltip, context);
        }
    });
    public static final Item SINGULARITY = register("singularity", new SingularityItem());
    public static final Item RAW_HEARTGOLD = registerItems("raw_heartgold",new Item(new Item.Settings().fireproof()));//粗制的哈尔特黄金
    public static final Item FLINT_AND_GOLD = registerItems("flint_and_gold",new Item(new Item.Settings().maxDamage(64)){//打金石
        @Override
        public void appendTooltip(ItemStack stack,World world,List<Text> tooltip,TooltipContext context){
            tooltip.add(Text.translatable("tooltip.heartgold.flint_and_gold"));
        }
    });
    public static final Item HEARTGOLD_SWORD = registerItems("heartgold_sword" ,new HeartGoldSword(ModToolMaterials.HEART_GOLD,//哈尔特黄金剑
            4,
            -2.0F,
            new Item.Settings().fireproof()){
        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("tooltip.heartgold.heartgold_sword"));
            super.appendTooltip(stack, world, tooltip, context);
        }
    });
    public static final Item HEARTGOLD_SHOVEL = registerItems("heartgold_shovel" ,new ShovelItem(ModToolMaterials.HEART_GOLD,//哈尔特黄金铲
            2.0F,
            -2.8F,
            new Item.Settings().fireproof()){
        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("tooltip.heartgold.heartgold_shovel"));
            super.appendTooltip(stack, world, tooltip, context);
        }
    });
    public static final Item HEARTGOLD_PICKAXE = registerItems("heartgold_pickaxe" ,new PickaxeItem(ModToolMaterials.HEART_GOLD,//哈尔特黄金镐
            2,
            -2.6F,
            new Item.Settings().fireproof()){
        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("tooltip.heartgold.heartgold_pickaxe"));
            super.appendTooltip(stack, world, tooltip, context);
        }
    });
    public static final Item HEARTGOLD_AXE = registerItems("heartgold_axe" ,new HeartGoldAxe(ModToolMaterials.HEART_GOLD,//哈尔特黄金斧
            10.0F,
            -3.4F,
            new Item.Settings().fireproof()){
        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("tooltip.heartgold.heartgold_axe"));
            super.appendTooltip(stack, world, tooltip, context);
        }
    });
    public static final Item HEARTGOLD_HOE = registerItems("heartgold_hoe" ,new HoeItem(ModToolMaterials.HEART_GOLD,//哈尔特黄金锄
            -4,
            -0.0F,
            new Item.Settings().fireproof()){
        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("tooltip.heartgold.heartgold_hoe"));
            super.appendTooltip(stack, world, tooltip, context);
        }
    });
    public static final Item GOLDPIG_SPAWN_EGG = registerItems("goldpig_spawn_egg",new SpawnEggItem(ModEntities.GOLDPIG,16638035,15375368,new Item.Settings()));//金猪刷怪蛋
    public static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM,RegistryKey.of(Registries.ITEM.getKey(),new Identifier(HeartGold.MOD_ID,id)),item);
    }
    public static final Item GOLDPORKCHOP = registerItems("gold_pork_chop",new Item(new FabricItemSettings().food(ModFoodComponents.GOLDPORKCHOP)));
public static Item register(String id, Item item) {
    return register(new Identifier(HeartGold.MOD_ID), item);
}


    public static Item register(Identifier id, Item item) {
        return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }


    public static Item register(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerItems() {
    }
}