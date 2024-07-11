package il.ac.hit.pooly;

import java.util.logging.Level;

/**
 * Represents a task that implements the Task interface. This task can be performed with a specified priority
 * and supports pausing and resuming functionality.
 */
public class MyTask implements Task {

    private int priority;
    private volatile boolean paused = false;
    private volatile boolean executed = false;

    /**
     * Performs the task logic. This method should handle any exceptions thrown during task execution.
     *
     * @throws TaskException if the task encounters an error condition during execution.
     */
    @Override
    public void perform() throws TaskException {
        LoggerConfig.logger.log(Level.INFO, "Task with priority " + priority + " started.");

        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                while (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new TaskException("Task interrupted while paused", e);
                    }
                }
            }

            // Task logic goes here
            LoggerConfig.logger.log(Level.INFO, "Performing task with priority " + priority);
            System.out.println("Performing task with priority " + priority);

            if (someConditionFails()) {
                throw new TaskException("Task failed due to some condition");
            }

            break; // Exit after performing the task once
        }

        LoggerConfig.logger.log(Level.INFO, "Task with priority " + priority + " completed.");
    }

    /**
     * Sets the priority level of the task.
     *
     * @param level the priority level to set for the task.
     */
    @Override
    public void setPriority(int level) {
        this.priority = level;
    }

    /**
     * Retrieves the current priority level of the task.
     *
     * @return the priority level of the task.
     */
    @Override
    public int getPriority() {
        return this.priority;
    }

    /**
     * Pauses the execution of the task. The task can be resumed later using the resume() method.
     */
    @Override
    public synchronized void pause() {
        this.paused = true;
        LoggerConfig.logger.log(Level.INFO, "Task with priority " + priority + " is paused");
    }

    /**
     * Resumes the execution of the task if it was previously paused.
     * Notifies any waiting threads that the task can be resumed.
     */
    @Override
    public synchronized void resume() {
        this.paused = false;
        notify();
        LoggerConfig.logger.log(Level.INFO, "Task with priority " + priority + " is resumed");
    }

    /**
     * Checks if the task is currently paused.
     *
     * @return true if the task is paused, false otherwise.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Checks if the task has been executed.
     *
     * @return true if the task has been executed, false otherwise.
     */
    public boolean isExecuted() {
        return executed;
    }

    /**
     * Simulates a condition check that may cause the task to fail.
     *
     * @return true if the condition fails, false otherwise.
     */
    private boolean someConditionFails() {
        // Simulate a condition that may cause task failure
        return false; // Modify to simulate failure conditions
    }
}
