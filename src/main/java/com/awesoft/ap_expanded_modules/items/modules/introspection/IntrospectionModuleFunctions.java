package com.awesoft.ap_expanded_modules.items.modules.introspection;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.MethodResult;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesComputer;
import de.srendi.advancedperipherals.common.smartglasses.SmartGlassesSideAccess;
import de.srendi.advancedperipherals.common.smartglasses.modules.IModuleFunctions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;

public class IntrospectionModuleFunctions implements IModuleFunctions {
    private final IntrospectionModule introspectionModule;
    private final SmartGlassesSideAccess access;

    public IntrospectionModuleFunctions(IntrospectionModule introspectionModule, SmartGlassesSideAccess access) {
        this.introspectionModule = introspectionModule;
        this.access = access;
    }

    @LuaFunction
    public final MethodResult getVelocity() {
        SmartGlassesComputer computer = access.getComputer();
        Entity entity = computer.getEntity();
        assert entity != null;

        Vec3 movement = entity.getDeltaMovement();
        return MethodResult.of(movement.x,movement.y,movement.z);
    }

    @LuaFunction
    public final int getImpulseCooldown() {
        return introspectionModule.impulseCooldown;
    }

    @LuaFunction
    public final boolean impulse(double x, double y, double z) {
        SmartGlassesComputer computer = access.getComputer();
        if (!computer.isEquipped()) {
            return false;
        }
        if (introspectionModule.impulseCooldown > 0) {
            return false;
        }
        Entity entity = computer.getEntity();
        assert entity != null;

        x = Math.max(x,-10);
        y = Math.max(y,-10);
        z = Math.max(z,-10);

        x = Math.min(x,10);
        y = Math.min(y,10);
        z = Math.min(z,10);

        entity.addDeltaMovement(new Vec3(x, y, z));
        entity.hasImpulse = true;
        entity.hurtMarked = true;
        introspectionModule.impulseCooldown = Math.max((int) entity.getDeltaMovement().length() *2,5);
        System.out.println(introspectionModule.impulseCooldown);
        return true;
    }

    @LuaFunction
    public final String getUsername() {
        SmartGlassesComputer computer = access.getComputer();
        if (!computer.isEquipped()) {
            return "";
        }
        return (String) computer.getEntity().getName().getString();
    }
}
