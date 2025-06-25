package com.heartgold;

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
                        ModItems.HEART_GOLD, // 贴图
                        Text.literal("Your First Dirt Block"), // 标题
                        Text.literal("Now make a three by three"), // 描述
                        new Identifier("heart-gold","textures/block/heartgold_block.png"), // 使用的背景图片
                        AdvancementFrame.TASK, // 选项: TASK, CHALLENGE, GOAL
                        true, // 在右上角显示
                        true, // 在聊天框公告
                        false // 没有达成时，不在进度页面里显示
                )
                // Criterion 中使用的第一个字符串是其他进度在需要 'requirements' 时引用的名字
                .criterion("got_heartgold", InventoryChangedCriterion.Conditions.items(ModItems.HEART_GOLD))
                .build(consumer, HeartGold.MOD_ID + "/root");

        Advancement gotOakAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        Items.OAK_LOG,
                        Text.literal("Your First Oak Block"),
                        Text.literal("Bare fisted"),
                        null, // 子进度不需要设置背景
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(1000))
                .criterion("got_wood", InventoryChangedCriterion.Conditions.items(Items.OAK_LOG))
                .build(consumer, HeartGold.MOD_ID + "/got_wood");

        Advancement eatAppleAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        Items.APPLE,
                        Text.literal("Apple and Beef"),
                        Text.literal("Ate an apple and beef"),
                        null, // 子进度不需要设置背景
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("ate_apple", ConsumeItemCriterion.Conditions.item(Items.APPLE))
                .criterion("ate_cooked_beef", ConsumeItemCriterion.Conditions.item(Items.COOKED_BEEF))
                .build(consumer, HeartGold.MOD_ID + "/ate_apple_and_beef");
    }
}