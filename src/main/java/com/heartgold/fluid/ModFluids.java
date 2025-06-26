package com.heartgold.fluid;

import com.heartgold.HeartGold;
import com.heartgold.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModFluids{
    public static FlowableFluid STILL_GOLD_WATER;
    public static FlowableFluid FLOWING_GOLD_WATER;
    public static Block GOLD_WATER_BLOCK;
    public static Item GOLD_WATER_BUCKET;


    public static void register(){
            STILL_GOLD_WATER = Registry.register(Registries.FLUID,
                    new Identifier(HeartGold.MOD_ID,"gold_water"),new GoldWaterFluid.Still());
            FLOWING_GOLD_WATER = Registry.register(Registries.FLUID,
                     new Identifier(HeartGold.MOD_ID,"flowing_gold_water"),new GoldWaterFluid.Flowing());

            GOLD_WATER_BLOCK = Registry.register(Registries.BLOCK,new Identifier(HeartGold.MOD_ID,"gold_water_block"),
                    new FluidBlock(ModFluids.STILL_GOLD_WATER, FabricBlockSettings.copyOf(Blocks.LAVA)){
                        @Override
                        public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
                            if (!world.isClient && entity instanceof LivingEntity) {
                                entity.damage(world.getDamageSources().lava(), 4.0F);
//                                entity.setFireTicks(15 * 20); // 燃烧15秒
                            }
                        }
                    });
            GOLD_WATER_BUCKET = Registry.register(Registries.ITEM,new Identifier(HeartGold.MOD_ID,"gold_water_bucket"),
                    new BucketItem(ModFluids.STILL_GOLD_WATER,new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

    }
}


