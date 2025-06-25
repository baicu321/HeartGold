package com.heartgold.block;

import com.heartgold.HeartGold;
import com.heartgold.block.HeartCraftingTable;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModBlocks {

    public static final Block HEARTGOLD_ORE = register(//哈尔特黄金矿石
            "heartgold_ore",
            new Block(AbstractBlock.Settings
                    .create()
                    .requiresTool()
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .strength(3.5F,3.0F)));
    public static final Block OVERFLOW_GOLD_ORE = register(//溢到爆表的金矿石
            "overflow_gold_ore",
            new Block(AbstractBlock.Settings
                    .create()
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.25F, 6.0F)));
    public static final Block OVERFLOW_DEEPSLATE_GOLD_ORE = register(//溢到爆表的深层金矿石
            "overflow_deepslate_gold_ore",
            new Block(AbstractBlock.Settings
                    .create()
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.25F, 6.0F)));
    public static final Block HEARTGOLD_BLOCK = register(//哈尔特黄金块
            "heartgold_block",
            new Block(AbstractBlock.Settings
                    .create()
                    .mapColor(MapColor.GOLD)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(5.5F, 1200.0F)));
    public static final Block HEART_PORTAL_BLOCK = register(//哈尔特传送门框架
            "heart_portal_block",
            new Block(AbstractBlock.Settings
                    .create()
                    .mapColor(MapColor.GOLD)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(5.5F,1200F)
            )
    );
    public static final Block HEART_CRAFTING_TABLE = register(//哈尔特工作台
            "heart_crafting_table",
            new HeartCraftingTable(FabricBlockSettings.create())
            );

    public static Block register(String id,Block block){
        registerBlockItems(id,block);
        return Registry.register(Registries.BLOCK,new Identifier(HeartGold.MOD_ID,id),block);
    }
    public static void registerBlockItems(String id,Block block){
        Registry.register(Registries.ITEM,new Identifier(HeartGold.MOD_ID,id),
            new BlockItem(block,new Item.Settings()));
    }

    public static void registerModBlocks(){

    }
}
