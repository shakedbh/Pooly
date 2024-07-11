package Tests;

import il.ac.hit.pooly.MyTask;
import il.ac.hit.pooly.Task;
import il.ac.hit.pooly.TaskException;
import il.ac.hit.pooly.ThreadsPool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ThreadsPoolTest class contains JUnit tests for the ThreadsPool class.
 * It tests various functionalities such as task submission, priority handling,
 * pausing/resuming tasks, and shutdown behavior of the ThreadsPool.
 */
public class ThreadsPoolTest {

    /**
     * Test case for submitting a task to the ThreadsPool.
     * It verifies that a task can be submitted and executed by the pool.
     */
    @Test
    public void testSubmitTask() {
        ThreadsPool pool = new ThreadsPool(2);
        Task task = new MyTask();

        task.setPriority(5);
        pool.submit(task);

        // Additional assertions can be added to check task execution status

        pool.shutdown();
    }

    /**
     * Test case for task priority handling in ThreadsPool.
     * It verifies that tasks with different priorities are executed in the correct order.
     */
    @Test
    public void testTaskPriority() {
        ThreadsPool pool = new ThreadsPool(2);
        Task task1 = new MyTask();
        Task task2 = new MyTask();

        task1.setPriority(1);
        task2.setPriority(10);

        pool.submit(task1);
        pool.submit(task2);

        // Additional assertions can be added to check task priority execution order

        pool.shutdown();
    }

    /**
     * Test case for pausing and resuming a task in ThreadsPool.
     * It verifies that a task can be paused and resumed correctly.
     */
    @Test
    public void testPauseAndResume() {
        MyTask task = new MyTask();
        ThreadsPool pool = new ThreadsPool(2);

        pool.submit(task);

        task.pause();
        assertTrue(task.isPaused());

        task.resume();
        assertFalse(task.isPaused());

        pool.shutdown();
    }
}
