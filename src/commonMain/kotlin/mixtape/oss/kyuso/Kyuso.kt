package mixtape.oss.kyuso

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import mixtape.oss.kyuso.backend.KyusoBackend
import mixtape.oss.kyuso.task.Task
import mixtape.oss.kyuso.tools.Runnable
import kotlin.time.Duration

/**
 * Using the specified [backend], this class is able to execute and schedule tasks.
 *
 * @param backend the [KyusoBackend] to use.
 */
public class Kyuso(internal val backend: KyusoBackend) {
    /**
     * A [CoroutineScope] that can be used to launch coroutines.
     */
    public val scope: CoroutineScope
        get() = backend.scope

    /**
     * The coroutine dispatcher for this [Kyuso] instance.
     */
    public val dispatcher: CoroutineDispatcher
        get() = backend.dispatcher

    /**
     * Dispatches the given [runnable].
     *
     * @param runnable the block to execute
     */
    public fun dispatch(runnable: Runnable<Unit>): Task {
        val task = backend.createExecutableTask(runnable)
        task.execute()

        return task
    }

    /**
     * Dispatches the given [runnable] in this thread pool after the given [delay].
     *
     * @param delay the delay before executing the runnable
     * @param runnable the runnable to execute
     */
    public fun dispatchAfter(delay: Duration, runnable: Runnable<Unit>): Task {
        val task = backend.createScheduledTask(runnable)
        task.executeAfter(delay)

        return task
    }

    /**
     * Dispatches the given [runnable] in this thread pool every [delay] milliseconds.
     *
     * @param delay the delay between executions
     * @param initialDelay the initial delay before the first execution.
     * @param runnable the block to execute.
     */
    public fun dispatchEvery(delay: Duration, initialDelay: Duration? = delay, runnable: Runnable<Unit>): Task =
        dispatchEvery({ delay }, initialDelay, runnable)

    /**
     * Dispatches the given [runnable] in this thread pool every [delay] milliseconds.
     *
     * @param delay the delay between executions
     * @param initialDelay the initial delay before the first execution.
     * @param runnable the block to execute.
     */
    public fun dispatchEvery(delay: () -> Duration, initialDelay: Duration? = delay(), runnable: Runnable<Unit>): Task {
        val task = backend.createScheduledTask(runnable)
        task.executePeriodically(delay, initialDelay)

        return task
    }

    /**
     * Dispatches the given [runnable] and returns its result.
     *
     * @param runnable the block to execute
     * @return the result of the block
     */
    public suspend fun <T> dispatchValue(runnable: Runnable<T>): T {
        val deferred = CompletableDeferred<T>()
        val task = backend.createExecutableTask {
            val value = runnable.run()
            deferred.complete(value)
        }

        task.execute()
        return deferred.await()
    }

    /**
     * Cancels this [Kyuso] instance and all of its active tasks.
     *
     * @throws IllegalStateException if this thread pool has already been shutdown
     */
    public fun cancel(cause: CancellationException?): Unit = backend.shutdown(cause)
}
