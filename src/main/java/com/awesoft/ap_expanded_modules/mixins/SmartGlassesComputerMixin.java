package com.awesoft.ap_expanded_modules.mixins;

import com.awesoft.ap_expanded_modules.items.modules.disk_drive.ISmartGlassesComputer;
import dan200.computercraft.api.peripheral.IComputerAccess;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesComputer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SmartGlassesComputer.class)
public class SmartGlassesComputerMixin implements ISmartGlassesComputer {
    @Unique
    IComputerAccess computerAccess;

    @Override
    public IComputerAccess getComputerAccess() {
        return computerAccess;
    }

    @Override
    public void setComputerAccess(IComputerAccess value) {
        computerAccess = value;
    }
}
