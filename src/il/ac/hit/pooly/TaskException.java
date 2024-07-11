package il.ac.hit.pooly;

/**
 * Custom exception class for handling exceptions related to tasks.
 */
public class TaskException extends Exception {

    /**
     * Constructs a TaskException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public TaskException(String message) {
        super(message);
    }

    /**
     * Constructs a TaskException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
