package mixtape.oss.kyuso.tools

import kotlin.math.max
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

/**
 * Creates an accurate delay for the given duration, accounts for any latency.
 *
 * @param delay the delay to wait for.
 */
@OptIn(ExperimentalTime::class)
public fun calculatingDelay(delay: Duration): () -> Duration {
    val mark = TimeSource.Monotonic.markNow()
    var nextFrameTimestamp = mark.elapsedNow().inWholeNanoseconds

    return {
        nextFrameTimestamp += delay.inWholeNanoseconds
        max(0, nextFrameTimestamp - mark.elapsedNow().inWholeNanoseconds).nanoseconds
    }
}
