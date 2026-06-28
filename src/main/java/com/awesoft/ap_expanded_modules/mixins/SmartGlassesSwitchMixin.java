package com.awesoft.ap_expanded_modules.mixins;

import com.awesoft.ap_expanded_modules.client.DiskDriveSlot;
import com.awesoft.ap_expanded_modules.client.DiskDriveTab;
import com.awesoft.ap_expanded_modules.client.ISmartGlassesScreen;
import de.srendi.advancedperipherals.client.screens.SmartGlassesScreen;
import de.srendi.advancedperipherals.client.widgets.SmartGlassesSettingsSwitch;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSlot;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmartGlassesSettingsSwitch.class)
public class SmartGlassesSwitchMixin {
    @Shadow
    private boolean isEnabled;

    @Final
    @Shadow
    private SmartGlassesScreen screen;

    @Inject(method = "onClick", at = @At("TAIL"))
    public void onClick(CallbackInfo ci) {
        if (isEnabled) {
            screen.renderables.forEach(renderable -> {
                if (renderable instanceof DiskDriveTab tab) {
                    tab.isEnabled = false;
                    ((ISmartGlassesScreen) screen).setDisk(false);
                }
            });
            for (Slot slot : screen.getMenu().slots) {
                if (slot instanceof DiskDriveSlot diskSlot) {
                    diskSlot.isActive = false;
                }
            }
        }
    }
}
