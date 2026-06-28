package com.awesoft.ap_expanded_modules.mixins;

import com.awesoft.ap_expanded_modules.items.modules.disk_drive.ISmartGlassesComputer;
import dan200.computercraft.api.peripheral.IComputerAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.ModulePeripheral;
import de.srendi.advancedperipherals.lib.peripherals.BasePeripheral;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BasePeripheral.class)
public abstract class AbstractPeripheralMixin {
    @Inject(method = "attach", at = @At("TAIL"))
    private void onAttach(IComputerAccess computer, CallbackInfo ci) {
        if (((BasePeripheral)(Object)this) instanceof ModulePeripheral periph) {
            ((ISmartGlassesComputer)periph.getPeripheralOwner().getComputer()).setComputerAccess(computer);
        }
    }

    @Inject(method = "detach", at = @At("TAIL"))
    private void onDetach(IComputerAccess computer, CallbackInfo ci) {
        if (((BasePeripheral)(Object)this) instanceof ModulePeripheral periph) {
            ((ISmartGlassesComputer)periph.getPeripheralOwner().getComputer()).setComputerAccess(null);
        }
    }
}
