package com.anthonymariotti.toolsavior.mixins;

import com.anthonymariotti.toolsavior.interfaces.mixins.ISaviorAnvilModification;
import com.anthonymariotti.toolsavior.utilities.SaviorLogger;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(AnvilScreenHandler.class)
public abstract class SaviorAnvilScreenHandlerModification implements ISaviorAnvilModification {
    @Inject(
            method = "updateResult()V",
            at = @At(
                    value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    public void isRepairing(CallbackInfo info) {
        SaviorLogger.info("Testing");
    }

    @Overwrite
    public static int getNextCost(int cost) {
        SaviorLogger.info("getNextCost Called");
        // TODO: Do testing to determine if this is working as intended.
        return 0;
    }
}
