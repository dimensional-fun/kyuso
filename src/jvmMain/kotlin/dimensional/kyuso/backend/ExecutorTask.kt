package dimensional.kyuso.backend

import dimensional.kyuso.task.EmptyTask
import dimensional.kyuso.task.ExecutableTask
import dimensional.kyuso.tools.Runnable
import dimensional.kyuso.tools.ext.synchronous

public class ExecutorTask(public val backend: ExecutorBackend, private val runnable: Runnable<*>) : ExecutableTask, EmptyTask() {
    override fun execute(): Unit = backend.executor.execute(runnable.synchronous)
}
