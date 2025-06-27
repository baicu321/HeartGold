package com.heartgold.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class HeartGoldAxe extends AxeItem {

    public HeartGoldAxe(ToolMaterial material,float attackDamage,float attackSpeed,Settings settings){
        super(material,attackDamage,attackSpeed,settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target,    LivingEntity attacker) {
        // 设置目标着火 5 秒 (100 ticks)
        target.setOnFireFor(5);

        if (target.isBlocking()) {
            target.disablesShield(); // 禁用盾牌
            target.getWorld().sendEntityStatus(target, (byte) 30); // 盾牌破裂动画
        }

        // 添加火焰粒子效果（使用瞬间伤害效果触发粒子）
        target.addStatusEffect(new StatusEffectInstance(
                StatusEffects.INSTANT_DAMAGE,
                1, // 持续时间（立即生效）
                0,
                false, // 环境粒子
                false, // 显示粒子
                true // 显示图标（可选）
        ));


        // 消耗耐久度
        stack.damage(1, attacker, (user) -> user.sendToolBreakStatus(user.getActiveHand()));

        return true;

    }
        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            float speed = super.getMiningSpeedMultiplier(stack, state);
            if (state.isIn(BlockTags.LOGS)) {
                return speed * 2.0f; // 对原木双倍挖掘速度
            }
            return speed;
        }
}
