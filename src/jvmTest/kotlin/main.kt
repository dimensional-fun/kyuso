import kotlinx.coroutines.*
import mixtape.oss.kyuso.Kyuso
import mixtape.oss.kyuso.tools.calculatingDelay
import java.util.concurrent.Executors
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@OptIn(ExperimentalTime::class)
suspend fun main() {
    val kyuso = Kyuso(Executors.newSingleThreadExecutor())

    val timeSource = TimeSource.Monotonic
    var last: TimeMark = timeSource.markNow()
    kyuso.dispatchEvery(calculatingDelay(20.milliseconds)) {
        println("hi, from last: ${last.elapsedNow()}")
        last = timeSource.markNow()
    }

    delay(1000)
}
