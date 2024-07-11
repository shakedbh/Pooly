package il.ac.hit.pooly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
    private static final String LOG_FILE_PATH = "logs/pooly.log";
    protected static final Logger logger = Logger.getLogger(LoggerConfig.class.getName());

    static {
        try {
            setupLogger();
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    private static void setupLogger() throws IOException {
        // Create log directory if not exists
        Path logFilePath = Paths.get(LOG_FILE_PATH);
        if (!Files.exists(logFilePath.getParent())) {
            Files.createDirectories(logFilePath.getParent());
        }

        // Create file handler to write logs to file
        FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, true);

        // Create a custom formatter for the log messages
        fileHandler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$s] [%3$s] -> %4$s: %5$s%6$s%n";

            @Override
            public synchronized String format(java.util.logging.LogRecord lr) {
                return String.format(format,
                        new java.util.Date(lr.getMillis()),
                        lr.getSourceClassName(),
                        lr.getSourceMethodName(),
                        lr.getLevel().getLocalizedName(),
                        formatMessage(lr),
                        System.lineSeparator());
            }
        });

        // Add file handler to logger
        logger.addHandler(fileHandler);
        logger.setLevel(Level.INFO); // Set default logging level
    }
}
