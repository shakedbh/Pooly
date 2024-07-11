package il.ac.hit.pooly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configures a global logger instance to write log messages to a specified file.
 * The logger configuration includes setting up a FileHandler for logging.
 */
public class LoggerConfig {

    /**
     * The logger instance used for logging messages.
     */
    protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Static initializer to set up the logger configuration when the class is loaded.
     */
    static {
        setupLogger();
    }

    /**
     * If the log file does not exist, it creates the necessary directories.
     */
    private static void setupLogger() {
        try {
            // Define log file path
            Path logFilePath = Paths.get("pooly.log");

            // Create parent directories if they don't exist
            Path parentDir = logFilePath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            // Create FileHandler to write to log file
            FileHandler fileHandler = new FileHandler(logFilePath.toString(), true);

            // Configure logger to use only the FileHandler
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO); // Adjust log level as needed

            // Remove default console handler if present
            Handler[] handlers = logger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    logger.removeHandler(handler);
                }
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up logger", e);
        }
    }
}
