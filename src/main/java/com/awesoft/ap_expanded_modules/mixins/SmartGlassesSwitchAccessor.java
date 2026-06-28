package com.awesoft.ap_expanded_modules.mixins;

import de.srendi.advancedperipherals.client.widgets.SmartGlassesSettingsSwitch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SmartGlassesSettingsSwitch.class)
public interface SmartGlassesSwitchAccessor {
    @Accessor("isEnabled")
    void setIsEnabled(boolean value);

    @Accessor("isEnabled")
    boolean getIsEnabled();
}
