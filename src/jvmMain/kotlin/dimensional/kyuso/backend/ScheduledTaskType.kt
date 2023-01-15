package dimensional.kyuso.backend

/**
 * Represents the types of scheduled tasks.
 */
public enum class ScheduledTaskType {
    /**
     * This task is scheduled to run using a [java.util.concurrent.ScheduledExecutorService].
     */
    Executor,

    /**
     * This task is scheduled to be run in a coroutine.
     */
    Coroutine
}
