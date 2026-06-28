package com.awesoft.ap_expanded_modules.items.modules.disk_drive;

import de.srendi.advancedperipherals.common.setup.APItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicReference;

public class GlassesHelper {
    public static ItemStack getGlassesItemFromPlayer(Player plr) {
        AtomicReference<ItemStack> returnStack = new AtomicReference<>();
        CuriosApi.getCuriosInventory(plr).ifPresent(inventory -> {
            inventory.findFirstCurio(item -> item.getItem().equals(APItems.SMART_GLASSES.get()) || item.getItem().equals(APItems.SMART_GLASSES_NETHERITE.get()))
                .ifPresent(slotResult -> {
                    ItemStack stack = slotResult.stack();
                    returnStack.set(stack);
                });
        });
        plr.getArmorSlots().forEach(item -> {
            if (item.is(APItems.SMART_GLASSES.get()) || item.is(APItems.SMART_GLASSES_NETHERITE.get())) {
                returnStack.set(item);
            }
        });
        return returnStack.get();
    }
}
