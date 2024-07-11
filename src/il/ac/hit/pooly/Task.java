package il.ac.hit.pooly;

/**
 * Interface representing a task with priority and control methods for execution.
 */
public interface Task {

    /**
     * Performs the task. This method contains the logic that defines what the task does.
     * This method may block if the task is paused.
     *
     * @throws TaskException if an error occurs during task execution.
     */
    public abstract void perform() throws TaskException;

    /**
     * Sets the priority of the task. The priority level determines the importance of the task.
     * Higher priority tasks should be executed before lower priority ones.
     *
     * @param level the priority level of the task.
     */
    public abstract void setPriority(int level);

    /**
     * Gets the priority of the task. This method returns the current priority level of the task.
     *
     * @return the priority level of the task.
     */
    public abstract int getPriority();

    /**
     * Pauses the task. This method should be implemented to pause the execution of the task.
     * When a task is paused, it should not proceed with its logic until it is resumed.
     */
    public abstract void pause();

    /**
     * Resumes the task. This method should be implemented to resume the execution of the task.
     * If the task was paused, it should continue its logic from the point it was paused.
     */
    public abstract void resume();
}
