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
        }

        @Override
        public void setPriority(int level) {
        }

        @Override
        public int getPriority() {
        }
        
        @Override
        public int pause() {
        }
        
        @Override
        public int resume() {
        }
    }
    ```

2. Use the `ThreadsPool` to manage tasks, for example:
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
- `void pause()`: Pause the task.
- `void resume()`: Resume the task.

### `ThreadsPool` Class

- `ThreadsPool(int numberOfThreads)`: Initializes the thread pool with the specified number of threads.
- `void submit(Task task)`: Submits a task to the pool.
- `void shutdown()`: Shuts down the thread pool gracefully.

## Testing

Unit tests are provided in the `Tests` directory. To run the tests, use your IDE's built-in test runner.

## Features

- `TaskException`: A custom exception class used for handling specific errors or exceptional conditions that tasks may encounter during execution.

- `LoggerConfig`: A utility class to configure logging settings and provide informative logs throughout the execution of tasks within the ThreadsPool.

