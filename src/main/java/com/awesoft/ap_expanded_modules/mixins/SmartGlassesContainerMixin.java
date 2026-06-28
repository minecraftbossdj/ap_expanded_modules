package com.awesoft.ap_expanded_modules.mixins;

import com.awesoft.ap_expanded_modules.client.DiskDriveSlot;
import com.awesoft.ap_expanded_modules.client.DiskDriveTab;
import com.awesoft.ap_expanded_modules.client.ISmartGlassesScreen;
import dan200.computercraft.shared.network.container.ComputerContainerData;
import de.srendi.advancedperipherals.common.container.SmartGlassesContainer;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesComputer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.neoforged.neoforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(SmartGlassesContainer.class)
public class SmartGlassesContainerMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(int id, Predicate<Player> canUse, SmartGlassesComputer computer, Inventory inventory, IItemHandler handler, ComputerContainerData data, CallbackInfo ci) {
        ((AbstractContainerMenuInvoker)(Object)this).invokeAddSlot(new DiskDriveSlot(handler, 11,222, 166));
    }

    @ModifyArg(
            method = "<init>(ILjava/util/function/Predicate;Ldan200/computercraft/shared/network/container/ComputerContainerData;Lnet/minecraft/world/entity/player/Inventory;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/SimpleContainer;<init>(I)V")
    )
    private static int modifyContainerSize(int original) {
        return original + 1;
    }

    @ModifyConstant(method = "quickMoveStack", constant = @Constant(intValue = 47))
    private int modifySlotCount(int original) {
        return original + 1;
    }
}
