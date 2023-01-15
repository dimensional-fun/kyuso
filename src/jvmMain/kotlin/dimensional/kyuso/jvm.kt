package dimensional.kyuso

import dimensional.kyuso.backend.ExecutorBackend
import java.util.concurrent.ExecutorService

/**
 * The current thread.
 */
public val currentThread: Thread
    get() = Thread.currentThread()

/**
 * The [ExecutorService] used to execute tasks, or null if this [Kyuso] instance does not use an executor-based backend.
 */
public val Kyuso.executor: ExecutorService?
    get() = if (backend is ExecutorBackend) backend.executor else null

/**
 * Creates a new [Kyuso] using the provided [executor].
 *
 * @param executor the executor service to use
 */
public fun Kyuso(executor: ExecutorService): Kyuso {
    return Kyuso(ExecutorBackend(executor))
}
