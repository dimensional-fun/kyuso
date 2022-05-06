package mixtape.oss.kyuso.tools.ext

import kotlinx.coroutines.runBlocking
import mixtape.oss.kyuso.tools.Runnable

public val Runnable<*>.synchronous: java.lang.Runnable
    get() = java.lang.Runnable {
        runBlocking { run() }
    }
