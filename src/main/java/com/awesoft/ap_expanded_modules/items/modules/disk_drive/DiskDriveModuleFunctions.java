package com.awesoft.ap_expanded_modules.items.modules.disk_drive;

import dan200.computercraft.api.lua.LuaFunction;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;

public class DiskDriveModuleFunctions implements IModuleFunctions {
    private final DiskDriveModule diskDriveModule;

    public DiskDriveModuleFunctions(DiskDriveModule diskDriveModule) {
        this.diskDriveModule = diskDriveModule;
    }

    @LuaFunction
    public final boolean isReal() {
        return true;
    }
}
