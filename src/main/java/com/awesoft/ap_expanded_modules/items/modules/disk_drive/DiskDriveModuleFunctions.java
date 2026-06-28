package com.awesoft.ap_expanded_modules.items.modules.disk_drive;

import com.awesoft.ap_expanded_modules.APExpandedModules;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.core.computer.ComputerSide;
import dan200.computercraft.shared.platform.PlatformHelper;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class DiskDriveModuleFunctions implements IModuleFunctions {
    private final DiskDriveModule diskDriveModule;
    private final SmartGlassesSideAccess smartGlassesSideAccess;

    public DiskDriveModuleFunctions(DiskDriveModule diskDriveModule, SmartGlassesSideAccess smartGlassesSideAccess) {
        this.smartGlassesSideAccess = smartGlassesSideAccess;
        this.diskDriveModule = diskDriveModule;
    }

    @LuaFunction
    public final boolean isReal(IComputerAccess access, int slot) {
        if (smartGlassesSideAccess.getEntity() instanceof Player plr) {
            var item = plr.getInventory().getItem(slot);
            var media = PlatformHelper.get().getMedia(item);
            var mount = media.createDataMount(item, (ServerLevel) plr.level());
            access.mount("test", mount);
        }
        return true;
    }
}
