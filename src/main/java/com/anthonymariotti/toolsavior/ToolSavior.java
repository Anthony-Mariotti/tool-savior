package com.anthonymariotti.toolsavior;

import com.anthonymariotti.toolsavior.utilities.SaviorLogger;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

public class ToolSavior implements ModInitializer {

    @Override
    public void onInitialize() {
        SaviorLogger.info("Initializing Tool Savior");
    }
}
