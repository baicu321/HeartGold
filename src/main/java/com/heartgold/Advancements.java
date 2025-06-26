package com.heartgold;

import com.heartgold.block.ModBlocks;
import com.heartgold.item.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class Advancements implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(
                        ModBlocks.HEART_CRAFTING_TABLE, // 贴图
                        Text.translatable("advancements.title.heart-crafting"), // 标题
                        Text.translatable("advancements.Content.heart-crafting"),// 描述
                        new Identifier("heart-gold","textures/block/heartgold_block.png"), // 使用的背景图片
                        AdvancementFrame.TASK, // 选项: TASK, CHALLENGE, GOAL
                        true, // 在右上角显示
                        true, // 在聊天框公告
                        false // 没有达成时，不在进度页面里显示
                )
                // Criterion 中使用的第一个字符串是其他进度在需要 'requirements' 时引用的名字
                .criterion("got_heart_crafting_table", InventoryChangedCriterion.Conditions.items(ModBlocks.HEART_CRAFTING_TABLE))
                .build(consumer, HeartGold.MOD_ID + "/root");

        Advancement gotOakAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        ModBlocks.HEART_PORTAL_BLOCK,
                        Text.translatable("advancements.title.heart-portal"),
                        Text.translatable("advancements.Content.heart-portal"),
                        null, // 子进度不需要设置背景
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(1000))
                .criterion("got_heart_portal_block", InventoryChangedCriterion.Conditions.items(ModBlocks.HEART_PORTAL_BLOCK))
                .build(consumer, HeartGold.MOD_ID + "/got_heart_portal_block");

    }
}