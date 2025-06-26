package com.heartgold.datagen;

import com.heartgold.fluid.ModFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.FluidTags;

import java.rmi.registry.Registry;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagGeneration extends FabricTagProvider.FluidTagProvider {
    public ModFluidTagGeneration(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completedFuture) {
        super(output, completedFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(FluidTags.LAVA)
                .add(ModFluids.STILL_GOLD_WATER)
                .add(ModFluids.FLOWING_GOLD_WATER);
    }
}
