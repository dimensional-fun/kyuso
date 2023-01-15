package dimensional.kyuso.backend

import dimensional.kyuso.task.ExecutableTask
import dimensional.kyuso.task.ScheduledTask
import dimensional.kyuso.tools.Runnable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

/**
 * The backend of a [dimensional.kyuso.Kyuso] instance, used to create executable and scheduled tasks.
 */
public interface KyusoBackend {
    /**
     * Coroutine scope used to launch coroutines.
     */
    public val scope: CoroutineScope

    /**
     * The dispatcher used to disaptch coroutines.
     */
    public val dispatcher: CoroutineDispatcher

    /**
     * Returns a list of tasks that are scheduled to run.
     */
    public fun createExecutableTask(runnable: Runnable<Unit>): ExecutableTask

    /**
     * Creates a new scheduled task.
     */
    public fun createScheduledTask(runnable: Runnable<Unit>): ScheduledTask

    /**
     * Shuts down this backend.
     */
    public fun shutdown() {
        shutdown(null)
    }

    /**
     * Shuts down this backend.
     */
    public fun shutdown(cause: CancellationException?)
}
