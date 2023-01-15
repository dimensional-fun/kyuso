package dimensional.kyuso.backend

import dimensional.kyuso.task.ExecutableTask
import dimensional.kyuso.tools.Runnable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

public class CoroutineTask(public val scope: CoroutineScope, public val runnable: Runnable<*>) : ExecutableTask {
    private lateinit var job: Job

    override fun execute() {
        job = scope.launch { runnable.run() }
    }

    override fun cancel(cause: CancellationException?) {
        job.cancel()
    }
}
