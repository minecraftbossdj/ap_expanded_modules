package com.awesoft.ap_expanded_modules.mixins;

import com.awesoft.ap_expanded_modules.APExpandedModules;
import com.awesoft.ap_expanded_modules.items.modules.disk_drive.ISmartGlassesComputer;
import dan200.computercraft.api.filesystem.Mount;
import dan200.computercraft.api.filesystem.WritableMount;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.pocket.IPocketUpgrade;
import dan200.computercraft.api.upgrades.UpgradeData;
import dan200.computercraft.impl.PocketUpgrades;
import dan200.computercraft.shared.platform.PlatformHelper;
import de.srendi.advancedperipherals.common.component.ItemStackStorage;
import de.srendi.advancedperipherals.common.items.SmartGlassesItem;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesComputer;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesItemHandler;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSlot;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

import static de.srendi.advancedperipherals.common.smartglasses.SmartGlassesItemHandler.loadItems;

@Mixin(SmartGlassesItemHandler.class)
public class SmartGlassesInvHandlerMixin {

    /**
     * @author minecraftbossdj
     * @reason need to add +1 for disk drive
     */
    @Overwrite
    public int getSlots() {
        return SmartGlassesSlot.SLOTS+1;
    }

    @Final
    @Shadow
    private SmartGlassesComputer computer;

    /**
     * @author minecraftbossdj
     * @reason same reason as above, disk drive
     */
    @Overwrite
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot >= SmartGlassesSlot.SLOTS+1) {
            return false;
        }
        if (stack.getItem() instanceof SmartGlassesItem) {
            return false;
        }
        if (slot == SmartGlassesSlot.SLOTS) {
            if (PlatformHelper.get().getMedia(stack) != null) return true;
        }
        ItemStackStorage items = loadItems(((SmartGlassesItemHandler)(Object)this).getGlasses());
        if (slot < SmartGlassesSlot.PERIPHERAL_SLOTS) {
            UpgradeData<IPocketUpgrade> upgradeData = PocketUpgrades.instance().get(computer.getLevel().registryAccess(), stack);
            if (upgradeData == null) {
                return false;
            }
            IPocketUpgrade upgrade = upgradeData.upgrade();
            if (!upgrade.isItemSuitable(stack)) {
                return false;
            }
            return true;
        }
        Item item = stack.getItem();
        if (!(item instanceof IModuleItem)) {
            return false;
        }
        for (int i = SmartGlassesSlot.MODULE_SLOT_OFFSET; i < SmartGlassesSlot.SLOTS; i++) {
            if (items.getItem(i) == item) {
                return false;
            }
        }
        if (slot == SmartGlassesSlot.SLOTS) {
            return PlatformHelper.get().getMedia(stack) != null;
        }
        return true;
    }

    @Final
    @Inject(method = "loadItems", at = @At("RETURN"), cancellable = true)
    private static void onLoadItems(ItemStack glasses, CallbackInfoReturnable<ItemStackStorage> cir) {
        ItemStackStorage storage = cir.getReturnValue();
        if (storage.size() == SmartGlassesSlot.SLOTS) {
            ItemStack[] old = storage.getAllUnsafe();
            ItemStackStorage newStorage = ItemStackStorage.ofSize(SmartGlassesSlot.SLOTS + 1);
            for (int i = 0; i < old.length; i++) {
                newStorage = newStorage.set(i, old[i]);
            }
            cir.setReturnValue(newStorage);
        }

    }

    @Unique
    private ItemStack previousDiskStack = ItemStack.EMPTY;

    @Inject(method = "setStackInSlot", at = @At("HEAD"), cancellable = true)
    public void onSetStackInSlot(int slot, ItemStack stack, CallbackInfo ci) {
        if (slot == SmartGlassesSlot.SLOTS) {
            ItemStack glasses = ((SmartGlassesItemHandler)(Object)this).getGlasses();
            ItemStackStorage items = SmartGlassesItemHandler.loadItems(glasses);
            SmartGlassesItemHandler.saveItems(glasses, items.set(slot, stack));
            IComputerAccess access = ((ISmartGlassesComputer)computer).getComputerAccess();
            if (!stack.isEmpty()) {
                var media = PlatformHelper.get().getMedia(stack);
                if (media != null) {
                    var mount = media.createDataMount(stack, computer.getLevel());
                    if (mount != null) {
                        if (access != null) access.mountWritable("disk", (WritableMount) mount);
                    }
                }
            } else if (!previousDiskStack.isEmpty()) {
                if (access != null) access.unmount("disk");
            }
            previousDiskStack = stack.copy();
            ci.cancel();
        }
    }
}
