package com.anthonymariotti.toolsavior.mixins;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public abstract class SaviorAnvilScreenModification {
    private static final Logger LOGGER = LoggerFactory.getLogger("Tool Savior");

    @Inject(method = "onSlotUpdate()V", at = @At("HEAD"))
    public void injectOnSlotUpdate(CallbackInfo info) {
        LOGGER.info("onSlotUpdate Called");
    }
}
