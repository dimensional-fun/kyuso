package dimensional.kyuso.backend

import dimensional.kyuso.task.ExecutableTask
import dimensional.kyuso.task.ScheduledTask
import dimensional.kyuso.tools.Runnable
import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.ScheduledExecutorService

public class ExecutorBackend(
    public val executor: ExecutorService,
    public val scheduledType: ScheduledTaskType = ScheduledTaskType.Coroutine,
) : KyusoBackend {
    override val dispatcher: CoroutineDispatcher by lazy {
        executor.asCoroutineDispatcher()
    }

    override val scope: CoroutineScope by lazy {
        CoroutineScope(dispatcher + SupervisorJob() + CoroutineName("ExecutorPool"))
    }

    init {
        if (scheduledType == ScheduledTaskType.Executor) require(executor is ScheduledExecutorService)
    }

    override fun createExecutableTask(runnable: Runnable<Unit>): ExecutableTask = ExecutorTask(this, runnable)

    override fun createScheduledTask(runnable: Runnable<Unit>): ScheduledTask {
        return if (scheduledType == ScheduledTaskType.Coroutine) {
            CoroutineScheduledTask(scope, runnable)
        } else {
            ExecutorScheduledTask(executor as ScheduledExecutorService, runnable)
        }
    }

    override fun shutdown(cause: CancellationException?) {
        executor.shutdownNow()
    }
}
