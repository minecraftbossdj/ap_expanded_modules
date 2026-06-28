package com.awesoft.ap_expanded_modules.mixins;

import com.awesoft.ap_expanded_modules.client.DiskDriveTab;
import com.awesoft.ap_expanded_modules.client.ISmartGlassesScreen;
import dan200.computercraft.shared.computer.inventory.AbstractComputerMenu;
import de.srendi.advancedperipherals.client.screens.SmartGlassesScreen;
import de.srendi.advancedperipherals.common.smartglasses.SlotType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmartGlassesScreen.class)
public abstract class SmartGlassesScreenMixin implements ISmartGlassesScreen {

    @Final
    @Shadow
    private static ResourceLocation BACKGROUND;

    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        ((ScreenInvoker)(Object)this).invokeAddRenderableWidget(new DiskDriveTab(254, 170+23, (SmartGlassesScreen)(Object)this));
    }

    @Shadow
    private SlotType currentType;

    @Inject(method = "renderBg", at = @At("TAIL"))
    public void renderBg(@NotNull GuiGraphics graphics, float partialTicks, int mouseX, int mouseY, CallbackInfo ci) {
        int leftPos = ((AbstractContainerScreenAccessor)(Object)this).getLeftPos();
        int topPos = ((AbstractContainerScreenAccessor)(Object)this).getTopPos();
        if (isDisk) {
            graphics.blit(BACKGROUND, leftPos + AbstractComputerMenu.SIDEBAR_WIDTH + 222, topPos + 183, 186, 183, 18, 18); //bottom right
            graphics.blit(BACKGROUND, leftPos + AbstractComputerMenu.SIDEBAR_WIDTH + 222, topPos + 183-18, 186, 183, 18, 18); //right
            graphics.blit(BACKGROUND, leftPos + AbstractComputerMenu.SIDEBAR_WIDTH + 222-36, topPos + 183-18, 186, 183, 18, 18); //left
            graphics.blit(BACKGROUND, leftPos + AbstractComputerMenu.SIDEBAR_WIDTH + 222-18, topPos + 183-36, 186, 183, 18, 18); //top
            graphics.blit(BACKGROUND, leftPos + AbstractComputerMenu.SIDEBAR_WIDTH + 222-18, topPos + 183, 186, 183, 18, 18); //top
        }
    }

    @ModifyVariable(method = "renderLabels", at = @At("STORE"), name = "invName")
    private FormattedCharSequence modifyInvName(FormattedCharSequence invName) {
        if (isDisk) {
            return Component.translatable("gui.ap_expanded_modules.smart_glasses.disk_drive").getVisualOrderText();
        }
        return invName;
    }

    @Unique
    private boolean isDisk;

    @Override
    public boolean isDisk() {
        return isDisk;
    }

    @Override
    public void setDisk(boolean value) {
        isDisk = value;
    }
}
