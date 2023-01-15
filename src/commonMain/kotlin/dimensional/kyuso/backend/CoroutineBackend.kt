package dimensional.kyuso.backend

import dimensional.kyuso.task.ExecutableTask
import dimensional.kyuso.task.ScheduledTask
import dimensional.kyuso.tools.Runnable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

/**
 * A backend that runs tasks in a coroutine.
 *
 * @param scope The coroutine scope to run tasks in.
 */
public open class CoroutineBackend(public override val scope: CoroutineScope) : KyusoBackend {
    @OptIn(ExperimentalStdlibApi::class)
    override val dispatcher: CoroutineDispatcher
        get() = scope.coroutineContext[CoroutineDispatcher] ?: error("No dispatcher found")

    override fun shutdown(cause: CancellationException?): Unit = scope.cancel(cause)

    override fun createExecutableTask(runnable: Runnable<Unit>): ExecutableTask = CoroutineTask(scope, runnable)

    override fun createScheduledTask(runnable: Runnable<Unit>): ScheduledTask = CoroutineScheduledTask(scope, runnable)
}
