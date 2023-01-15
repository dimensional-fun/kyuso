package dimensional.kyuso.tools.ext

import dimensional.kyuso.tools.Runnable
import kotlinx.coroutines.runBlocking

public val Runnable<*>.synchronous: java.lang.Runnable
    get() = java.lang.Runnable {
        runBlocking { run() }
    }
