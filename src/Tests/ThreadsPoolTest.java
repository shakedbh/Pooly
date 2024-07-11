package Tests;

import il.ac.hit.pooly.MyTask;
import il.ac.hit.pooly.Task;
import il.ac.hit.pooly.TaskException;
import il.ac.hit.pooly.ThreadsPool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadsPoolTest {
    @Test
    public void testSubmitTask() {
        ThreadsPool pool = new ThreadsPool(2);
        Task task = new MyTask();

        task.setPriority(5);
        pool.submit(task);

        // Add assertions to check task execution
        pool.shutdown();
    }

    @Test
    public void testTaskPriority() {
        ThreadsPool pool = new ThreadsPool(2);
        Task task1 = new MyTask();
        Task task2 = new MyTask();

        task1.setPriority(1);
        task2.setPriority(10);

        pool.submit(task1);
        pool.submit(task2);

        // Add assertions to check task priority handling
        pool.shutdown();
    }

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
