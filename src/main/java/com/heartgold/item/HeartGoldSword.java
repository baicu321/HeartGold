package com.heartgold.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class HeartGoldSword extends SwordItem {
        public HeartGoldSword(ToolMaterial material,int attackDamage, float attackSpeed,Settings settings){
            super(material,attackDamage,attackSpeed,settings);
        }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 设置目标着火 5 秒 (100 ticks)
        target.setOnFireFor(5);

        // 可选：添加火焰粒子效果
        target.addStatusEffect(new StatusEffectInstance(
                StatusEffects.FIRE_RESISTANCE,  // 使用火焰抗性触发粒子效果
                2,
                0,
                false,
                false,
                true
        ));

        return super.postHit(stack, target, attacker);
    }

}
