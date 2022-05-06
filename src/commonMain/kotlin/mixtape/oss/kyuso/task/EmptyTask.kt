package mixtape.oss.kyuso.task

import kotlinx.coroutines.CancellationException

/**
 * Represents an empty task, which is a task that cannot be cancelled.
 */
public open class EmptyTask : Task {
    public companion object : EmptyTask()

    override fun cancel(cause: CancellationException?): Nothing = TODO()
}
