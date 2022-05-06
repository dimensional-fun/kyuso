package mixtape.oss.kyuso.backend

import kotlinx.coroutines.*
import mixtape.oss.kyuso.task.ScheduledTask
import mixtape.oss.kyuso.tools.Runnable
import kotlin.time.Duration

public class CoroutineScheduledTask(
    public val scope: CoroutineScope,
    private val runnable: Runnable<*>,
) : ScheduledTask {
    private lateinit var job: Job

    override fun executeAfter(delay: Duration): ScheduledTask {
        require(!::job.isInitialized) { "This 'ScheduledTask' has already been scheduled, use cancel before rescheduling it." }

        job = scope.launch {
            delay(delay)
            runnable.run()
        }

        return this
    }

    override fun executePeriodically(period: () -> Duration, initialDelay: Duration?): ScheduledTask {
        require(!::job.isInitialized) { "This 'ScheduledTask' has already been scheduled, use cancel before rescheduling it." }

        job = scope.launch {
            var initial = true
            while (isActive) {
                if (initial) {
                    initial = false
                    delay(initialDelay ?: period())
                }

                runnable.run()
                delay(period())
            }
        }

        return this
    }

    override fun cancel(cause: CancellationException?) {
        require(::job.isInitialized) { "Task has not yet been scheduled." }
        job.cancel()
    }
}
