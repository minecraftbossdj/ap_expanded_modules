package com.awesoft.ap_expanded_modules.items.modules.test;

import dan200.computercraft.api.lua.LuaFunction;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;
import de.srendi.advancedperipherals.common.smartglasses.modules.nightvision.NightVisionModule;

public class TestModuleFunctions implements IModuleFunctions {
    private final TestModule testModule;

    public TestModuleFunctions(TestModule testModule) {
        this.testModule = testModule;
    }

    @LuaFunction
    public final boolean isDebug() {
        return true;
    }
}
