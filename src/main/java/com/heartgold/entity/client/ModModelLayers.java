package com.heartgold.entity.client;

import com.heartgold.HeartGold;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer GOLDPIG =
            new EntityModelLayer(new Identifier(HeartGold.MOD_ID,"goldpig"),"main");
    public static final EntityModelLayer SAND_HAND =
            new EntityModelLayer(new Identifier(HeartGold.MOD_ID,"sand_hand"),"main");
}
