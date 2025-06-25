package com.heartgold.screen;

import com.heartgold.gui.CraftingSlot;
import com.heartgold.gui.OutputSlot;
import com.heartgold.registry.HeartScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;


public class HeartCraftingScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public HeartCraftingScreenHandler(int syndId,PlayerInventory playerInventory){
        this(syndId , playerInventory,new SimpleInventory(26));
    }
    public HeartCraftingScreenHandler(int syndId,PlayerInventory playerInventory,Inventory inventory) {
        super(HeartScreenHandlers.HEART_CRAFTING_SCREEN_HANDLER, syndId);
        checkSize(inventory, 1);
        this.inventory = inventory;
        this.inventory.onOpen(playerInventory.player);
        this.addCraftingSlots(inventory);
        this.addSlot(new OutputSlot(inventory, 25, 191, 77));
        this.addPlayerInventory(playerInventory);
        this.addPlayerHotbar(playerInventory);
        this.inventory.markDirty();
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (25 < invSlot && invSlot < 53) {
                if (!this.insertItem(originalStack, 0, 26, false))
                    return ItemStack.EMPTY;
            } else if (invSlot == 25) {
                if (!this.insertItem(originalStack, 26, 53, false))
                    return ItemStack.EMPTY;
                else
                    for (int i = 0; i < 25; ++i)
                        if (this.getSlot(i).getStack().getCount() != 0)
                            this.getSlot(i).getStack().setCount(this.getSlot(i).getStack().getCount() - 1);
            } else if (!this.insertItem(originalStack, 26, 53, false))
                return ItemStack.EMPTY;
            if (originalStack.isEmpty())
                slot.setStack(ItemStack.EMPTY);
            else
                slot.markDirty();
        }
        return newStack;
                    }



            public boolean canUse(PlayerEntity player){
                return true;
            }
        private void addPlayerInventory(PlayerInventory playerInventory){
                    for (int i = 0; i < 3; ++i)
                        for (int l = 0;l < 9; ++l)
                            this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 48 + l * 18,165 + i * 18));

        }
public void onClosed(PlayerEntity player) {

        super.onClosed(player);
        this.inventory.onClose(player);

}
    private void addPlayerHotbar(PlayerInventory playerInventory) {
            for (int i =0;i<9;++i)
                this.addSlot(new Slot(playerInventory, i ,48 + i*18,223));
    }
    private void addCraftingSlots(Inventory inventory){
        for (int i =0; i<5;++i)
            for(int l =0;l<5;++l)
                this.addSlot(new CraftingSlot(inventory,l + i*5,49+l*18,24+i *18));
    }

            public void onSlotClick(int slotIndex, int button, SlotActionType actionType,PlayerEntity player){
            super.onSlotClick(slotIndex,button,actionType,player);
                if (slotIndex>=0)
                    this.slots.get(slotIndex).markDirty();

        }
}
