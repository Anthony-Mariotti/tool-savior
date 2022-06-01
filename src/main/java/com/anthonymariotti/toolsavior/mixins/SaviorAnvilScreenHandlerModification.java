package com.anthonymariotti.toolsavior.mixins;

import com.anthonymariotti.toolsavior.interfaces.mixins.ISaviorAnvilModification;
import com.anthonymariotti.toolsavior.utilities.SaviorLogger;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;


@Mixin(AnvilScreenHandler.class)
public abstract class SaviorAnvilScreenHandlerModification implements ISaviorAnvilModification {

    private static boolean isEnchantment;

    @Inject(
            method = "updateResult()V",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isDamageable()Z",
                    ordinal = 0,
                    shift = At.Shift.BEFORE
            ),
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
                    ),
                    to = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;isDamageable()Z"
                    )
            )
    )
    public void isEnchantment(CallbackInfo info, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map<Enchantment, Integer> map, boolean bl) {
        if (bl) {
            SaviorLogger.info("Running in enchantment mode");
            isEnchantment = true;
            return;
        }

        SaviorLogger.info("Running in repair mode");

        // TODO: Just renaming naming check
    }

    @Overwrite
    public static int getNextCost(int cost) {
        if (!isEnchantment) {
            SaviorLogger.info("Not Enchantment, returning 0");
            return 0;
        }

        SaviorLogger.info("Other, returning original calculation");
        return cost * 2 + 1;
    }
}
