package com.awesoft.ap_expanded_modules.items.modules.disk_drive;

import com.awesoft.ap_expanded_modules.APExpandedModules;
import dan200.computercraft.api.filesystem.Mount;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.core.computer.ComputerSide;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesComputer;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesItemHandler;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModule;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.ItemStackTagFix;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskDriveModule implements IModule {

    public DiskDriveModule() {}

    private ItemStack glasses = ItemStack.EMPTY;

    @Override
    public @NotNull ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(APExpandedModules.MODID, "disk_drive");
    }

    @Override
    public @Nullable IModuleFunctions getFunctions(SmartGlassesSideAccess smartGlassesSideAccess) {
        return new DiskDriveModuleFunctions(this, smartGlassesSideAccess);
    }

    @Override
    public void serverTick(SmartGlassesSideAccess smartGlassesAccess) {}

    @Override
    public void onUnequipped(SmartGlassesSideAccess smartGlassesAccess) {
        var glasses = GlassesHelper.getGlassesItemFromPlayer((Player) smartGlassesAccess.getEntity());
        if (!glasses.isEmpty() && glasses != null) {
            var items = SmartGlassesItemHandler.loadItems(glasses);
            var disk = items.get(11).copy();
            if (!disk.isEmpty()) {
                items = items.set(11, ItemStack.EMPTY);
                SmartGlassesItemHandler.saveItems(glasses, items);
                ((Player) smartGlassesAccess.getEntity()).drop(disk, false);
                ((ISmartGlassesComputer)smartGlassesAccess.getComputer()).getComputerAccess().unmount("disk");
            }
        }
    }
}
