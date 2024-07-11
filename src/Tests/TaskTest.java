package Tests;

import il.ac.hit.pooly.MyTask;
import il.ac.hit.pooly.Task;
import il.ac.hit.pooly.TaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TaskTest class contains JUnit tests for the MyTask class.
 * It tests various functionalities such as setting/getting priority,
 * performing tasks with and without exceptions, and pausing/resuming tasks.
 */
public class TaskTest {

    /**
     * Test case for setPriority(int level) and getPriority() methods.
     * It sets a priority for a task and verifies if the priority is correctly retrieved.
     */
    @Test
    public void testSetAndGetPriority() {
        Task task = new MyTask();

        task.setPriority(5);
        assertEquals(5, task.getPriority());
    }

    /**
     * Test case for perform() method of MyTask class.
     * It verifies that perform() method executes without throwing exceptions.
     */
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

    /**
     * Test case for perform() method of MyTask class that throws TaskException.
     * It verifies that perform() method throws TaskException with the expected message.
     */
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

    /**
     * Test case for pause() and resume() methods of MyTask class.
     * It verifies that pause() sets the task to paused state and resume() resumes it.
     */
    @Test
    public void testPauseAndResume() {
        MyTask task = new MyTask();
        task.pause();
        assertTrue(task.isPaused());

        task.resume();
        assertFalse(task.isPaused());
    }
}
