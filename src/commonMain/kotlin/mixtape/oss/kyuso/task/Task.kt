package mixtape.oss.kyuso.task

import kotlinx.coroutines.CancellationException

/**
 * Represents a task that has been dispatched.
 */
public interface Task {
    /**
     * Cancels this task with the specified [cause].
     *
     * @param cause the cause of the cancellation
     * @throws IllegalStateException if this task has already been cancelled
     */
    public fun cancel(cause: CancellationException?)

    /**
     * Cancels this task with no cause.
     * @throws IllegalStateException if this task has already been cancelled
     */
    public fun cancel() {
        cancel(null)
    }
}
