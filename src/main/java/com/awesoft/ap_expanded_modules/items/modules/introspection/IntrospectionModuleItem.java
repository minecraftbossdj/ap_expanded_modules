package com.awesoft.ap_expanded_modules.items.modules.introspection;

import com.awesoft.ap_expanded_modules.items.modules.introspection.IntrospectionModule;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleItem;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class IntrospectionModuleItem extends Item implements IModuleItem<IntrospectionModule> {
    public IntrospectionModuleItem() {
        super(new Properties());
    }

    @Override
    public @NotNull IntrospectionModule createModule(SmartGlassesSideAccess smartGlassesSideAccess) {
        return new IntrospectionModule();
    }
}
