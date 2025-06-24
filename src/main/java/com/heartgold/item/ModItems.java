package com.heartgold.item;

import com.heartgold.HeartGold;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public class ModItems {
    public static final Item HEART_GOLD = registerItems("heartgold",new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item RAW_HEARTGOLD = registerItems("raw_heartgold",new Item(new Item.Settings()));
    public static final Item SINGULARITY = register("singularity", new SingularityItem());
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