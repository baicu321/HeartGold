package com.heartgold.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class HeartGoldScreen extends Screen {
    protected HeartGoldScreen(){
        super(Text.literal("标题111111"));
    }

    public ButtonWidget button1;
    public ButtonWidget button2;

    protected void init(){
        button1 = ButtonWidget.builder(Text.literal("按钮1"),button ->{} )
                .dimensions(width / 2 -205,20,200,20)
                .tooltip(Tooltip.of(Text.literal("这是个提示")))
                .build();
        button2 = ButtonWidget.builder(Text.literal("按钮2"),button ->{} )
                .dimensions(width / 2 -205,20,200,20)
                .tooltip(Tooltip.of(Text.literal("这是个提示")))
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
    }
}
