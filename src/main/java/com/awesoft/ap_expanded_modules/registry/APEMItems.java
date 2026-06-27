package com.awesoft.ap_expanded_modules.registry;

import com.awesoft.ap_expanded_modules.items.modules.disk_drive.DiskDriveModuleItem;
import com.awesoft.ap_expanded_modules.items.modules.introspection.IntrospectionModule;
import com.awesoft.ap_expanded_modules.items.modules.introspection.IntrospectionModuleItem;
import com.awesoft.ap_expanded_modules.items.modules.test.TestModuleItem;
import de.srendi.advancedperipherals.common.setup.APRegistration;
import de.srendi.advancedperipherals.common.smartglasses.modules.nightvision.NightVisionModuleItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.awesoft.ap_expanded_modules.APExpandedModules.MODID;

public class APEMItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredHolder<Item, TestModuleItem> DEBUG_MODULE = ITEMS.register("debug_module", TestModuleItem::new);
    public static final DeferredHolder<Item, DiskDriveModuleItem> DISK_DRIVE_MODULE = ITEMS.register("disk_drive_module", DiskDriveModuleItem::new);
    public static final DeferredHolder<Item, IntrospectionModuleItem> INTROSPECTION_MODULE = ITEMS.register("introspection_module", IntrospectionModuleItem::new);

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
