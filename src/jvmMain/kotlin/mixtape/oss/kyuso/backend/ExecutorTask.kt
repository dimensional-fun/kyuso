package mixtape.oss.kyuso.backend

import mixtape.oss.kyuso.task.EmptyTask
import mixtape.oss.kyuso.task.ExecutableTask
import mixtape.oss.kyuso.tools.ext.synchronous

public class ExecutorTask(public val backend: ExecutorBackend, private val runnable: mixtape.oss.kyuso.tools.Runnable<*>) : ExecutableTask, EmptyTask() {
    override fun execute(): Unit = backend.executor.execute(runnable.synchronous)
}
