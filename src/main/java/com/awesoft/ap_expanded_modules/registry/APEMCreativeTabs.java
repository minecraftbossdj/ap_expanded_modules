package com.awesoft.ap_expanded_modules.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.awesoft.ap_expanded_modules.APExpandedModules.MODID;

public class APEMCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup.ap_expanded_modules.main"))
        .withTabsBefore(CreativeModeTabs.COMBAT)
        .icon(() -> APEMItems.DISK_DRIVE_MODULE.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            output.accept(APEMItems.DISK_DRIVE_MODULE.get());
            output.accept(APEMItems.INTROSPECTION_MODULE.get());
        }
    ).build());

}
