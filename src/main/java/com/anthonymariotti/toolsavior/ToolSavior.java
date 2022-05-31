package com.anthonymariotti.toolsavior;

import com.anthonymariotti.toolsavior.utilities.SaviorLogger;
import net.fabricmc.api.DedicatedServerModInitializer;

public class ToolSavior implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        SaviorLogger.info("Initializing Tool Savior");
    }
}
