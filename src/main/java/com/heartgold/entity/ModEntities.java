package com.heartgold.entity;

import com.heartgold.HeartGold;
import com.heartgold.entity.custom.GoldPigEntity;
import com.heartgold.entity.custom.SandHandEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModEntities {
    public static final EntityType<GoldPigEntity> GOLDPIG = Registry.register(Registries.ENTITY_TYPE,//碰撞箱
            new Identifier(HeartGold.MOD_ID,"goldpig"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,GoldPigEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());
    public static final  EntityType<SandHandEntity> SAND_HAND = Registry.register
            (Registries.ENTITY_TYPE,
            new Identifier(HeartGold.MOD_ID,"sand_hand"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SandHandEntity::new)
                            .dimensions(EntityDimensions.changing(12f,20f))
                            .build());

    public static void register(){
        FabricDefaultAttributeRegistry.register(ModEntities.GOLDPIG, GoldPigEntity.createGoldPigAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SAND_HAND, SandHandEntity.createSandHandAttributes());
    }
}
