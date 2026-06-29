package com.awesoft.ap_expanded_modules.items.modules.disk_drive;

import com.awesoft.ap_expanded_modules.APExpandedModules;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.core.computer.ComputerSide;
import dan200.computercraft.shared.platform.PlatformHelper;
import de.srendi.advancedperipherals.common.component.ItemStackStorage;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesItemHandler;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DiskDriveModuleFunctions implements IModuleFunctions {
    private final DiskDriveModule diskDriveModule;
    private final SmartGlassesSideAccess smartGlassesSideAccess;

    public DiskDriveModuleFunctions(DiskDriveModule diskDriveModule, SmartGlassesSideAccess smartGlassesSideAccess) {
        this.smartGlassesSideAccess = smartGlassesSideAccess;
        this.diskDriveModule = diskDriveModule;
    }

    @LuaFunction
    public final boolean hasDisk(IComputerAccess access) {
        if (smartGlassesSideAccess.getEntity() instanceof Player plr) {
            var item = GlassesHelper.getGlassesItemFromPlayer(plr);
            ItemStackStorage items = SmartGlassesItemHandler.loadItems(item);
            return !items.getItem(11).asItem().equals(Items.AIR);
        }
        return false;
    }

    @LuaFunction
    public final void ejectDisk(IComputerAccess access) {
        if (smartGlassesSideAccess.getEntity() instanceof Player plr) {
            var glasses = GlassesHelper.getGlassesItemFromPlayer(plr);
            var items = SmartGlassesItemHandler.loadItems(glasses);
            var disk = items.get(11).copy();
            if (!disk.isEmpty()) {
                items = items.set(11, ItemStack.EMPTY);
                SmartGlassesItemHandler.saveItems(glasses, items);
                plr.drop(disk, false);
                access.unmount("disk");
            }
        }
    }
}
