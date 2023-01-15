package dimensional.kyuso

import dimensional.kyuso.backend.CoroutineBackend
import dimensional.kyuso.task.Task
import dimensional.kyuso.tools.Runnable
import kotlinx.coroutines.*

/**
 * Creates a new coroutine-based [Kyuso] using the specified [dispatcher].
 *
 * @param dispatcher the [CoroutineDispatcher] to use
 */
public fun Kyuso(dispatcher: CoroutineDispatcher): Kyuso {
    return Kyuso(scope = CoroutineScope(dispatcher + SupervisorJob() + CoroutineName("Kyuso")))
}

/**
 * Creates a new coroutine-based [Kyuso] using the specified [scope].
 *
 * @param scope the [CoroutineScope] to use
 */
public fun Kyuso(scope: CoroutineScope): Kyuso {
    return Kyuso(CoroutineBackend(scope))
}


/**
 * Executes the specified [block].
 *
 * @param block the block to execute
 * @return A [Task] that can be used to cancel the execution.
 */
public operator fun Kyuso.invoke(block: Runnable<Unit>): Task {
    return dispatch(block)
}

/**
 * Cancels this pool with the specified [reason].
 *
 * @param reason the reason to cancel this pool and all of its tasks.
 */
public fun Kyuso.cancel(reason: String) {
    cancel(CancellationException(reason))
}

/**
 * Creates and launches a new coroutine with the specified [block].
 *
 * @param block the block to execute
 * @return A [Job] that can be used to cancel the coroutine.
 */
public fun Kyuso.launch(block: suspend CoroutineScope.() -> Unit): Job {
    return scope.launch { block() }
}


/**
 * Creates a new coroutine, and it's future result as an implementation of [Deferred].
 *
 * @param block the block to execute
 * @return the coroutine's future result
 */
public fun <T> Kyuso.async(block: suspend CoroutineScope.() -> T): Deferred<T> {
    return scope.async { block() }
}
