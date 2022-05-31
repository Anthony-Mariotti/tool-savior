package com.anthonymariotti.toolsavior.mixins;

import com.anthonymariotti.toolsavior.interfaces.mixins.ISaviorAnvilModification;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public abstract class SaviorAnvilScreenHandlerModification implements ISaviorAnvilModification {
    private static final Logger TSLOGGER = LoggerFactory.getLogger("ToolSavior");

    @Shadow
    private Property levelCost;

    @Shadow
    private int repairItemUsage;

    private Boolean isEnchantment;

    @Inject(
            method = "updateResult()V",
            at = @At(
                    value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    public void isRepairing(CallbackInfo info) {
        TSLOGGER.info("Testing");
    }

    @Overwrite
    public static int getNextCost(int cost) {
        TSLOGGER.info("getNextCost Called");
        // TODO: Do testing to determine if this is working as intended.
        return 0;
    }
}
