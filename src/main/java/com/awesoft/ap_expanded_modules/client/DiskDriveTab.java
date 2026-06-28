package com.awesoft.ap_expanded_modules.client;

import com.awesoft.ap_expanded_modules.APExpandedModules;
import com.awesoft.ap_expanded_modules.items.modules.disk_drive.DiskDriveModule;
import com.awesoft.ap_expanded_modules.items.modules.disk_drive.DiskDriveModuleItem;
import com.awesoft.ap_expanded_modules.mixins.SmartGlassesSwitchAccessor;
import com.awesoft.ap_expanded_modules.registry.APEMItems;
import dan200.computercraft.core.computer.ComputerSide;
import dan200.computercraft.shared.computer.inventory.AbstractComputerMenu;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.client.screens.SmartGlassesScreen;
import de.srendi.advancedperipherals.client.widgets.SmartGlassesSettingsSwitch;
import de.srendi.advancedperipherals.common.smartglasses.SlotType;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSlot;
import de.srendi.advancedperipherals.common.smartglasses.modules.ModulePeripheral;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DiskDriveTab extends AbstractWidget {
    private static final ResourceLocation BACKGROUND = AdvancedPeripherals.getRL("textures/gui/smart_glasses_gui.png");

    private final SmartGlassesScreen screen;
    public boolean isEnabled;

    public DiskDriveTab(int x, int y, SmartGlassesScreen screen) {
        super(screen.getGuiLeft() + x + AbstractComputerMenu.SIDEBAR_WIDTH, screen.getGuiTop() + y, 21, 22, Component.translatable("gui.ap_expanded_modules.smart_glasses.disk_drive"));
        this.screen = screen;
    }

    public boolean checkItem() {
        return screen.getMenu().slots.stream()
                .filter(slot -> slot instanceof SmartGlassesSlot s && s.slotType == SlotType.MODULES)
                .anyMatch(slot -> slot.hasItem() && slot.getItem().is(APEMItems.DISK_DRIVE_MODULE.get()));
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        if (!checkItem()) return;
        if (isEnabled) {
            graphics.blit(BACKGROUND, this.getX() - 3, this.getY(), 45, 217, 24, 22);
        } else {
            graphics.blit(BACKGROUND, this.getX(), this.getY(), 23, 217, 21, 22);
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (this.isEnabled) {
            return;
        }

        if (!checkItem()) return;

        for (Slot slot : screen.getMenu().slots) {
            if (slot instanceof SmartGlassesSlot smartGlassesSlot) {
                smartGlassesSlot.setActiveSlotType(null);
            }
            if (slot instanceof DiskDriveSlot diskSlot) {
                diskSlot.isActive = true;
            }
        }
        screen.renderables.forEach(renderable -> {
            if (renderable instanceof SmartGlassesSettingsSwitch smartGlassesSettingsSwitch) {
                ((SmartGlassesSwitchAccessor)smartGlassesSettingsSwitch).setIsEnabled(false);
            }
        });
        this.isEnabled = true;
        screen.setCurrentType(SlotType.PERIPHERALS);
        ((ISmartGlassesScreen) screen).setDisk(true);
    }

    public void renderTooltip(GuiGraphics gui, int x, int y) {
        if (screen != null && isMouseOver(x, y)) {
            gui.renderTooltip(Minecraft.getInstance().font, Component.translatable("gui.ap_expanded_modules.smart_glasses.disk_drive"), x, y);
        }
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput pNarrationElementOutput) {
    }
}
