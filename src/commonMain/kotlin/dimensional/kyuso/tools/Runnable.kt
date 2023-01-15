package dimensional.kyuso.tools

/**
 * A that can be used with suspending functions.
 */
public fun interface Runnable<T> {
    public suspend fun run(): T
}
