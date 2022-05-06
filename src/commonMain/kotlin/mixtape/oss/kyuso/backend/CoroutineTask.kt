package mixtape.oss.kyuso.backend

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mixtape.oss.kyuso.task.ExecutableTask
import mixtape.oss.kyuso.tools.Runnable

public class CoroutineTask(public val scope: CoroutineScope, public val runnable: Runnable<*>) : ExecutableTask {
    private lateinit var job: Job

    override fun execute() {
        job = scope.launch { runnable.run() }
    }

    override fun cancel(cause: CancellationException?) {
        job.cancel()
    }
}
