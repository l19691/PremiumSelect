package util;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtility {
    private static final Logger logger = Logger.getLogger("PremiumSelectLogger");

    static {
        try {
            FileHandler handler = new FileHandler("premiumselect.log", true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setUseParentHandlers(false); // Disable console logs
        } catch (IOException e) {
            System.err.println("Logger failed to initialize: " + e.getMessage());
        }
    }

    public static void log(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }
}

