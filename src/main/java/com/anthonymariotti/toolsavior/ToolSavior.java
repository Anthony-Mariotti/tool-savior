package com.anthonymariotti.toolsavior;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolSavior implements DedicatedServerModInitializer {
    private static Logger LOGGER = LoggerFactory.getLogger("ToolSavior");

    @Override
    public void onInitializeServer() {
        LOGGER.info("Initializing Tool Savior");
    }
}
