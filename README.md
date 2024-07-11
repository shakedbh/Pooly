# pooly

## Introduction

The pooly library is a multithreading library developed in Java, designed to manage multiple threads efficiently. The library includes a `ThreadsPool` class and a `Task` interface, allowing for prioritized task execution.

## Setup

1. Ensure JDK 22 is installed.
2. Clone the repository and open it in your IDE.

## Usage

1. Create a `Task` implementation:
    ```java
    public class MyTask implements Task {
        private int priority;

        @Override
        public void perform() {
            // Task implementation
        }

        @Override
        public void setPriority(int level) {
            this.priority = level;
        }

        @Override
        public int getPriority() {
            return this.priority;
        }
    }
    ```

2. Use the `ThreadsPool` to manage tasks:
    ```java
    public class Main {
        public static void main(String[] args) {
            ThreadsPool pool = new ThreadsPool(4);
            MyTask task = new MyTask();
            task.setPriority(10);
            pool.submit(task);
            pool.shutdown();
        }
    }
    ```

## API Documentation

### `Task` Interface

- `void perform()`: Executes the task.
- `void setPriority(int level)`: Sets the task priority.
- `int getPriority()`: Gets the task priority.

### `ThreadsPool` Class

- `ThreadsPool(int numberOfThreads)`: Initializes the thread pool with the specified number of threads.
- `void submit(Task task)`: Submits a task to the pool.
- `void shutdown()`: Shuts down the thread pool gracefully.

## Testing

Unit tests are provided in the `test` directory. To run the tests, use your IDE's built-in test runner or execute the following command:

```bash
./gradlew test
