package com.anthonymariotti.toolsavior.mixins;

import com.anthonymariotti.toolsavior.interfaces.mixins.ISaviorAnvilModification;
import com.anthonymariotti.toolsavior.utilities.SaviorLogger;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;


@Mixin(AnvilScreenHandler.class)
public abstract class SaviorAnvilScreenHandlerModification implements ISaviorAnvilModification {

    private boolean isEnchantment;
    private boolean hasRename;

    @Shadow
    @Final
    private Property levelCost;

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
            SaviorLogger.info("Level Cost: " + levelCost);
            isEnchantment = true;
            return;
        }

        SaviorLogger.info("Running in repair mode");
        SaviorLogger.info("Level Cost: " + levelCost);
        // TODO: Just renaming naming check
        levelCost.set(0);
    }

    @Redirect(
            method = "updateResult()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;setRepairCost(I)V"
            )
    )
    public void setReducedRepairCost(ItemStack itemStack2, int t) {
        if (!isEnchantment) {

            if (hasRename) {
                SaviorLogger.info("Setting renamed cost");
                SaviorLogger.info("Level Cost: " + levelCost.get());
                itemStack2.setRepairCost(itemStack2.getRepairCost());
                return;
            }

            SaviorLogger.info("Setting 0 repair cost");
            SaviorLogger.info("Level Cost: " + levelCost.get());
            itemStack2.setRepairCost(0);
            return;
        }

        SaviorLogger.info("Setting original repair cost");
        SaviorLogger.info("Level Cost: " + levelCost.get());
        itemStack2.setRepairCost(t);
    }

    @Redirect(
            method = "updateResult()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;setCustomName(Lnet/minecraft/text/Text;)Lnet/minecraft/item/ItemStack;"
            )
    )
    public ItemStack hookRename(ItemStack itemStack2, Text text) {
        SaviorLogger.info("Hooked Rename");
        SaviorLogger.info("Level Cost: " + levelCost.get());
        hasRename = true;
        return itemStack2.setCustomName(text);
    }
}
