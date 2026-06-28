package com.awesoft.ap_expanded_modules.client;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class DiskDriveSlot extends SlotItemHandler {

    public boolean isActive = false;

    public DiskDriveSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }
}
