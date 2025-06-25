package com.heartgold.client.screen;

import com.heartgold.screen.HeartCraftingScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HeartCraftingScreen extends HandledScreen<HeartCraftingScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("heart-gold", "textures/gui/heart_crafting.png");

    public HeartCraftingScreen(HeartCraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler,inventory,title);
        this.backgroundWidth = 256;
        this.backgroundHeight = 256;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        titleY = 5;
    }
protected void drawForeground(DrawContext context,int mouseX, int mouseY){
//        context.drawText(
//                this.textRenderer,
//                this.title,
//                this.titleX,
//                this.titleY,
//                0xFFD700,
//                false
//        );
}
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}