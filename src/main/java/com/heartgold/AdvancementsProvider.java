package com.heartgold;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class AdvancementsProvider extends FabricAdvancementProvider {
    protected AdvancementsProvider(FabricDataOutput dataGenerator){
        super(dataGenerator);
    }
    @Override
    public void generateAdvancement(Consumer<Advancement> consumer){
        new Advancements().accept(consumer);
    }
}
