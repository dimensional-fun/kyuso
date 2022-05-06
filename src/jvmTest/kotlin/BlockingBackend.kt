import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import mixtape.oss.kyuso.backend.KyusoBackend
import mixtape.oss.kyuso.task.EmptyTask
import mixtape.oss.kyuso.task.ExecutableTask
import mixtape.oss.kyuso.task.ScheduledTask
import mixtape.oss.kyuso.tools.Runnable
import mixtape.oss.kyuso.tools.ext.synchronous

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
