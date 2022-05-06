package mixtape.oss.kyuso.backend

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import mixtape.oss.kyuso.task.ExecutableTask
import mixtape.oss.kyuso.task.ScheduledTask
import mixtape.oss.kyuso.tools.Runnable

/**
 * The backend of a [mixtape.oss.kyuso.Kyuso] instance, used to create executable and scheduled tasks.
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
