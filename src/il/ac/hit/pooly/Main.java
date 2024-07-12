package il.ac.hit.pooly;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Main class demonstrates the usage of ThreadsPool and Task classes.
 * It creates a thread pool, submits tasks with different priorities,
 * demonstrates task pausing and resuming, and shuts down the pool after
 * all tasks are complete.
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * The main method starts the execution of the program.
     * It initializes logging, creates a thread pool with 3 threads,
     * submits tasks with different priorities, demonstrates task pausing
     * and resuming, and shuts down the pool after all tasks are complete.
     */
    public static void main(String[] args) {
        System.out.println(AnsiColors.GREEN_BOLD + "Pooly starting..." + AnsiColors.RESET);

        // Create a ThreadsPool with 3 threads
        ThreadsPool pool = new ThreadsPool(3);

        // Create and submit tasks with different priorities
        submitTasks(pool);

        // Demonstrate pausing and resuming tasks
        MyTask pauseTask = createPauseTask();
        pauseTask.setPriority(10);
        pool.submit(pauseTask);

        pauseAndResumeTask(pauseTask);

        // Shutdown the pool after all tasks are complete
        shutdownPool(pool);

        System.out.println(AnsiColors.GREEN_BOLD + "Pooly finished." + AnsiColors.RESET);
    }

    /**
     * Creates and submits tasks with different priorities to the specified thread pool.
     *
     * @param pool the ThreadsPool instance where tasks will be submitted
     */
    private static void submitTasks(ThreadsPool pool) {
        for (int i = 1; i <= 5; i++) {
            MyTask task = new MyTask();
            task.setPriority(i);
            pool.submit(task);
        }
    }

    /**
     * Creates a MyTask instance that demonstrates pausing and resuming behavior.
     *
     * @return a MyTask instance configured with pausing and resuming behavior
     */
    private static MyTask createPauseTask() {
        return new MyTask() {
            @Override
            public void perform() throws TaskException {
                System.out.println("PauseTask started with priority " + getPriority());
                super.perform(); // This will block if paused
                System.out.println("PauseTask resumed and completed");
            }
        };
    }

    /**
     * Pauses and resumes the specified MyTask instance to demonstrate task control.
     *
     * @param pauseTask the MyTask instance to pause and resume
     */
    private static void pauseAndResumeTask(MyTask pauseTask) {
        try {
            Thread.sleep(1000); // Allow some time for tasks to start
            pauseTask.pause();
            System.out.println("PauseTask is paused");

            Thread.sleep(2000); // Simulate some delay
            pauseTask.resume();
            System.out.println("PauseTask is resumed");
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Task interruption occurred", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    /**
     * Shuts down the specified ThreadsPool after allowing time for tasks to complete.
     *
     * @param pool the ThreadsPool instance to shut down
     */
    private static void shutdownPool(ThreadsPool pool) {
        try {
            Thread.sleep(5000); // Allow time for tasks to complete
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Shutdown delay interruption occurred", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        pool.shutdown();
    }
}
