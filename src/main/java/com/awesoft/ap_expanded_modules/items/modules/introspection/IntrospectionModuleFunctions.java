package com.awesoft.ap_expanded_modules.items.modules.introspection;

import dan200.computercraft.api.lua.LuaFunction;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;

public class IntrospectionModuleFunctions implements IModuleFunctions {
    private final IntrospectionModule introspectionModule;

    public IntrospectionModuleFunctions(IntrospectionModule introspectionModule) {
        this.introspectionModule = introspectionModule;
    }

    @LuaFunction
    public final boolean isReal() {
        return true;
    }
}
