import dimensional.kyuso.backend.KyusoBackend
import dimensional.kyuso.task.EmptyTask
import dimensional.kyuso.task.ExecutableTask
import dimensional.kyuso.task.ScheduledTask
import dimensional.kyuso.tools.Runnable
import dimensional.kyuso.tools.ext.synchronous
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

object BlockingBackend : KyusoBackend {
    override val scope: CoroutineScope
        get() = TODO()

    override val dispatcher: CoroutineDispatcher
        get() = TODO()

    override fun createExecutableTask(runnable: Runnable<Unit>): ExecutableTask {
        return object : ExecutableTask, EmptyTask() {
            override fun execute() = runnable.synchronous.run()
        }
    }

    override fun createScheduledTask(runnable: Runnable<Unit>): ScheduledTask {
        TODO("Not yet implemented")
    }

    override fun shutdown(cause: CancellationException?) {
    }
}
