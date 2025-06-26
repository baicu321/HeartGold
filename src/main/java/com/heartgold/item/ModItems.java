package com.heartgold.item;

import com.heartgold.HeartGold;
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
    public static final Item HEART_GOLD = registerItems("heartgold",new Item(new Item.Settings().rarity(Rarity.EPIC)){
        @Override
        public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("tooltip.heartgold.heart_gold"));
            super.appendTooltip(stack, world, tooltip, context);
        }
    });
    public static final Item RAW_HEARTGOLD = registerItems("raw_heartgold",new Item(new Item.Settings()));
    public static final Item SINGULARITY = register("singularity", new SingularityItem());
    public static final Item HEARTGOLD_SWORD = registerItems("heartgold_sword" ,new SwordItem(ModToolMaterials.HEART_GOLD,
            3,
            -2.0F,
            new Item.Settings()));
    public static final Item HEARTGOLD_SHOVEL = registerItems("heartgold_shovel" ,new ShovelItem(ModToolMaterials.HEART_GOLD,
            1.5F,
            -2.0F,
            new Item.Settings()));
    public static final Item HEARTGOLD_PICKAXE = registerItems("heartgold_pickaxe" ,new PickaxeItem(ModToolMaterials.HEART_GOLD,
            2,
            -2.0F,
            new Item.Settings()));
    public static final Item HEARTGOLD_AXE = registerItems("heartgold_axe" ,new AxeItem(ModToolMaterials.HEART_GOLD,
            6.0F,
            -1.0F,
            new Item.Settings()));
    public static final Item HEARTGOLD_HOE = registerItems("heartgold_hoe" ,new HoeItem(ModToolMaterials.HEART_GOLD,
            -4,
            -2.0F,
            new Item.Settings()));
    public static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM,RegistryKey.of(Registries.ITEM.getKey(),new Identifier(HeartGold.MOD_ID,id)),item);
    }
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