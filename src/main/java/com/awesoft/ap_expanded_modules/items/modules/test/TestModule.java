package com.awesoft.ap_expanded_modules.items.modules.test;

import com.awesoft.ap_expanded_modules.APExpandedModules;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModule;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestModule implements IModule {
    public TestModule() {
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(APExpandedModules.MODID, "debug");
    }

    @Override
    public @Nullable IModuleFunctions getFunctions(SmartGlassesSideAccess smartGlassesSideAccess) {
        return new TestModuleFunctions(this);
    }
}
