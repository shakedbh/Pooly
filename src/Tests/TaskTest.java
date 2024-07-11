package Tests;

import il.ac.hit.pooly.MyTask;
import il.ac.hit.pooly.Task;
import il.ac.hit.pooly.TaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testSetAndGetPriority() {
        Task task = new MyTask();

        task.setPriority(5);
        assertEquals(5, task.getPriority());
    }

    @Test
    public void testPerform() {
        MyTask task = new MyTask();
        task.setPriority(5);

        try {
            task.perform();
        } catch (TaskException e) {
            fail("perform() threw an unexpected exception: " + e.getMessage());
        }

        // Since perform doesn't change any state in MyTask, we don't assert anything here
    }

    @Test
    public void testPerformWithException() {
        Task task = new MyTask() {
            @Override
            public void perform() throws TaskException {
                throw new TaskException("Simulated exception");
            }
        };

        TaskException thrown = assertThrows(TaskException.class, task::perform);
        assertEquals("Simulated exception", thrown.getMessage());
    }

    @Test
    public void testPauseAndResume() {
        MyTask task = new MyTask();
        task.pause();
        assertTrue(task.isPaused());

        task.resume();
        assertFalse(task.isPaused());
    }
}
