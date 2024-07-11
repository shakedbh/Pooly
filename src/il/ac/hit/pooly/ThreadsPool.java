package il.ac.hit.pooly;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * A thread pool that manages tasks of type 'Task' with varying priorities.
 * Tasks are executed in order of their priority, with higher priority tasks executed first.
 */
public class ThreadsPool extends LoggerConfig {

    private final ExecutorService executorService;
    private final PriorityBlockingQueue<Task> taskQueue;

    /**
     * Constructs a thread pool with a specified number of threads.
     *
     * @param numberOfThreads the number of threads in the pool.
     */
    public ThreadsPool(int numberOfThreads) {
        super(); // Call the constructor of LoggerConfig to initialize logging

        // Initialize ExecutorService with fixed number of threads
        executorService = Executors.newFixedThreadPool(numberOfThreads);

        // Initialize PriorityBlockingQueue for task prioritization
        taskQueue = new PriorityBlockingQueue<>(11, (t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()));

        // Start worker threads to execute tasks
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                while (!executorService.isShutdown()) {
                    try {
                        Task task = taskQueue.take(); // Retrieve task with highest priority
                        task.perform(); // Perform the task
                    } catch (TaskException e) {
                        logger.log(Level.SEVERE, "Task execution failed: " + e.getMessage());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Unexpected error: " + e.getMessage());
                    }
                }
            });
        }
    }

    /**
     * Submits a task to the thread pool for execution.
     *
     * @param task the task to be executed.
     */
    public void submit(Task task) {
        synchronized (taskQueue) {
            taskQueue.offer(task); // Add task to the priority queue
        }
    }

    /**
     * Initiates an orderly shutdown of the thread pool. Waits for up to 60 seconds for
     * tasks to complete execution before forcefully terminating them.
     */
    public void shutdown() {
        try {
            logger.log(Level.INFO, "Shutting down thread pool...");
            executorService.shutdown(); // Initiate shutdown

            // Wait for up to 60 seconds for tasks to complete
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                logger.log(Level.WARNING, "Forcing shutdown of thread pool due to timeout.");
                executorService.shutdownNow(); // Forceful shutdown if tasks do not complete
            }
            else {
                logger.log(Level.INFO, "Thread pool shutdown completed.");
            }
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Thread pool shutdown interrupted. Forcing immediate shutdown.");
            executorService.shutdownNow(); // Forceful shutdown on interruption
            Thread.currentThread().interrupt();
        }
    }
}
