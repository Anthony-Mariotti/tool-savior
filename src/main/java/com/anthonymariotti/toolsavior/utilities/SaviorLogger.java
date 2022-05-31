package com.anthonymariotti.toolsavior.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface SaviorLogger {
    Logger LOGGER = LoggerFactory.getLogger("ToolSavior");

    static void info(String message) {
        LOGGER.info(message);
    }
}
