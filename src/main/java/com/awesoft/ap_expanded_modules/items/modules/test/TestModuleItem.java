package com.awesoft.ap_expanded_modules.items.modules.test;

import de.srendi.advancedperipherals.common.items.base.BaseItem;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleItem;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class TestModuleItem extends Item implements IModuleItem<TestModule> {
    public TestModuleItem() {
        super(new Item.Properties());
    }

    @Override
    public @NotNull TestModule createModule(SmartGlassesSideAccess smartGlassesSideAccess) {
        return new TestModule();
    }
}
