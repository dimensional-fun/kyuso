package dimensional.kyuso.backend

import dimensional.kyuso.task.ScheduledTask
import dimensional.kyuso.tools.Runnable
import dimensional.kyuso.tools.ext.synchronous
import kotlinx.coroutines.CancellationException
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

public class ExecutorScheduledTask(
    public val executor: ScheduledExecutorService,
    public val runnable: Runnable<Unit>
) : ScheduledTask {
    private lateinit var future: ScheduledFuture<*>

    override fun executeAfter(delay: Duration): ScheduledTask {
        future = executor.schedule(runnable.synchronous, delay.inWholeNanoseconds, TimeUnit.NANOSECONDS)
        return this
    }

    override fun executePeriodically(period: () -> Duration, initialDelay: Duration?): ScheduledTask {
        val delay = period().inWholeNanoseconds
        val initial = initialDelay?.inWholeNanoseconds ?: delay
        future = executor.scheduleWithFixedDelay(runnable.synchronous, initial, delay, TimeUnit.NANOSECONDS)

        return this
    }

    override fun cancel(cause: CancellationException?) {
        future.cancel(cause != null)
    }
}
