package com.heartgold;

import com.heartgold.block.ModBlocks;
import com.heartgold.fluid.ModFluids;
import com.heartgold.item.ModItemGroups;
import com.heartgold.item.ModItems;
import com.heartgold.registry.HeartBlockEntitiles;
import com.heartgold.registry.HeartRecipes;
import com.heartgold.registry.HeartResourceManagers;
import com.heartgold.screen.HeartCraftingScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class HeartGold implements ModInitializer {
	public static final String MOD_ID = "heart-gold";


	public static ScreenHandlerType<HeartCraftingScreenHandler> HEART_CRAFTING_HANDLER;
	public static final RegistryKey<PlacedFeature> OVERFLOWGOLD = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID,"overflow_gold"));
//			Registry.register(
//					Registries.SCREEN_HANDLER,
//					new Identifier(MOD_ID, "HeartGoldScreen"),
//					new ScreenHandlerType<>(HeartCraftingScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES)
////					new ExtendedScreenHandlerType<>
////							new HeartCraftingScreenHandler(syncId, inv, ScreenHandlerContext.EMPTY)
//					);
//			);

		public static final RegistryKey<DimensionType> HEART_TYPE =
				RegistryKey.of(RegistryKeys.DIMENSION_TYPE,new Identifier("heart-gold","heart_type"));
		public static final RegistryKey<World> HEART =
			RegistryKey.of(RegistryKeys.WORLD, new Identifier("heart-gold", "heart"));
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
		@Override
		public void onInitialize() {
//			Registry.register(Registries.RECIPE_TYPE,
//					new Identifier(MOD_ID,"heart_crafting"),
//					 RecipeType.CRAFTING);
			HEART_CRAFTING_HANDLER = Registry.register(
					Registries.SCREEN_HANDLER,
					new Identifier(MOD_ID,"heart_crafting"),
					new ScreenHandlerType<>(HeartCraftingScreenHandler::new,FeatureFlags.DEFAULT_ENABLED_FEATURES)
			);
			HeartRecipes.init();
			HeartResourceManagers.register();
			HeartBlockEntitiles.init();
			ModBlocks.registerModBlocks();
			ModItems.registerItems();
			ModFluids.register();
			Registry.register(Registries.PARTICLE_TYPE, new Identifier("heart-gold", "dx_particle"), DX_PARTICLE);
			ModItemGroups.registGroups();
			CustomPortalBuilder.beginPortal()//自定义传送门api调用
					.frameBlock(ModBlocks.HEART_PORTAL_BLOCK)
					.lightWithItem(ModItems.FLINT_AND_GOLD)
					.destDimID(Identifier.of("heart-gold","heart"))
					.tintColor(245, 255, 0)
					.onlyLightInOverworld()
					.registerPortal();
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,OVERFLOWGOLD);

		}
	public static final DefaultParticleType DX_PARTICLE = FabricParticleTypes.simple();
	}
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
