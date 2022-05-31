package com.anthonymariotti.toolsavior.mixins;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ForgingScreenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgingScreenHandler.class)
public class SaviorForingScreenHandlerModification {
    private static final Logger TSLOGGER = LoggerFactory.getLogger("ToolSavior");

    @Shadow
    protected Inventory input;

    @Inject(method = "onContentChanged()V", at = @At("HEAD"))
    public void injectOnContentChanged(Inventory inventory, CallbackInfo info) {
        TSLOGGER.info("=============================================");
        TSLOGGER.info("onContentChanged");
        TSLOGGER.info("Input 00: " + input.getStack(0).);
        TSLOGGER.info("Input 01: " + input.getStack(1).getName().asString());
        TSLOGGER.info("=============================================");
    }
}
