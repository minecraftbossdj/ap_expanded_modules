package com.awesoft.ap_expanded_modules.items.modules.introspection;

import com.awesoft.ap_expanded_modules.APExpandedModules;
import com.awesoft.ap_expanded_modules.items.modules.introspection.IntrospectionModuleFunctions;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModule;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IntrospectionModule implements IModule {
    public IntrospectionModule() {
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(APExpandedModules.MODID, "introspection");
    }

    @Override
    public @Nullable IModuleFunctions getFunctions(SmartGlassesSideAccess smartGlassesSideAccess) {
        return new IntrospectionModuleFunctions(this);
    }
}