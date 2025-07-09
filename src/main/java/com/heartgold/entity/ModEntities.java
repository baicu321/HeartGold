package com.heartgold.entity;

import com.heartgold.HeartGold;
import com.heartgold.entity.custom.GoldPigEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<GoldPigEntity> GOLDPIG = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(HeartGold.MOD_ID,"goldpig"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,GoldPigEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());
}
