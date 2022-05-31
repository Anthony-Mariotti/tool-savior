package com.anthonymariotti.toolsavior.mixins;

import com.anthonymariotti.toolsavior.interfaces.mixins.ISaviorAnvilModification;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class SaviorAnvilScreenHandlerModification implements ISaviorAnvilModification {
    private static final Logger TSLOGGER = LoggerFactory.getLogger("ToolSavior");

    @Shadow
    private Property levelCost;

    @Shadow
    private int repairItemUsage;


    @Inject(method = "updateResult()V", at = @At("HEAD"))
    public void injectUpdateResult(CallbackInfo info) {
        TSLOGGER.info("=============================================");
        TSLOGGER.info("Updating Result");
        TSLOGGER.info("Level Cost: " + levelCost.get());
        TSLOGGER.info("Repair Item Usage: " + repairItemUsage);
        TSLOGGER.info("=============================================");
    }

    @Overwrite
    public static int getNextCost(int cost) {
        TSLOGGER.info("getNextCost Called");
        return 1;
    }
}
