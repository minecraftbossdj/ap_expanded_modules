package com.awesoft.ap_expanded_modules.items.modules.disk_drive;

import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleItem;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class DiskDriveModuleItem extends Item implements IModuleItem<DiskDriveModule> {
    public DiskDriveModuleItem() {
        super(new Properties());
    }

    @Override
    public @NotNull DiskDriveModule createModule(SmartGlassesSideAccess smartGlassesSideAccess) {
        return new DiskDriveModule();
    }
}
