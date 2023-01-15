package dimensional.kyuso.task

import kotlin.time.Duration

public interface ScheduledTask : Task {
    /**
     * Executes this task once, after the specified [delay].
     *
     * @param delay how long to wait (in milliseconds) before executing the task.
     * @return `ScheduledTask`, useful for chaining.
     */
    public fun executeAfter(delay: Duration): ScheduledTask

    /**
     * Executes this every [period] milliseconds, and after the [initialDelay].
     *
     * @param initialDelay how long to wait (in milliseconds) before starting this task.
     * @param period how long to wait (in milliseconds) between each execution.
     * @return `ScheduledTask`, useful for chaining.
     */
    public fun executePeriodically(period: Duration, initialDelay: Duration): ScheduledTask =
        executePeriodically({ period }, initialDelay)

    /**
     * Executes this every [period], and after the [initialDelay].
     *
     * @param initialDelay function used to calculate how long to wait before starting this task.
     * @param period the [Duration] to wait between each execution.
     * @return `ScheduledTask`, useful for chaining.
     */
    public fun executePeriodically(period: () -> Duration, initialDelay: Duration?): ScheduledTask
}
