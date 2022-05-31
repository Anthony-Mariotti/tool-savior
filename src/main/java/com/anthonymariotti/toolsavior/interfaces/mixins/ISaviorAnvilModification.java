package com.anthonymariotti.toolsavior.interfaces.mixins;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public interface ISaviorAnvilModification {
    void isRepairing(CallbackInfo info);
}
