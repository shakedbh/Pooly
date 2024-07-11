package il.ac.hit.pooly;

/**
 * The Main class demonstrates the usage of ThreadsPool and Task classes.
 * It creates a thread pool, submits tasks with different priorities,
 * demonstrates task pausing and resuming, and shuts down the pool after
 * all tasks are complete.
 */
public class Main {

    /**
     * The main method starts the execution of the program.
     * It initializes logging, creates a thread pool with 3 threads,
     * submits tasks with different priorities, demonstrates task pausing
     * and resuming, and shuts down the pool after all tasks are complete.
     */
    public static void main(String[] args) {

        LoggerConfig.logger.info("Main method starting...");
        // Create a ThreadsPool with 3 threads
        ThreadsPool pool = new ThreadsPool(3);

        // Create and submit tasks with different priorities
        for (int i = 1; i <= 5; i++) {
            MyTask task = new MyTask();
            task.setPriority(i);
            pool.submit(task);
        }

        // Demonstrate pausing and resuming tasks
        MyTask pauseTask = new MyTask() {
            @Override
            public void perform() throws TaskException {
                System.out.println("PauseTask started with priority " + getPriority());
                super.perform(); // This will block if paused
                System.out.println("PauseTask resumed and completed");
            }
        };
        pauseTask.setPriority(10);
        pool.submit(pauseTask);

        // Pause the task
        try {
            Thread.sleep(1000); // Allow some time for tasks to start
            pauseTask.pause();
            System.out.println("PauseTask is paused");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simulate some delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Resume the task
        pauseTask.resume();
        System.out.println("PauseTask is resumed");

        // Shutdown the pool after all tasks are complete
        try {
            Thread.sleep(5000); // Allow time for tasks to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        LoggerConfig.logger.info("Main method finished.");
    }
}
