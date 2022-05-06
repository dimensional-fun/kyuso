# Kyuso

Kyuso is a simple and flexible library for executing and scheduling tasks using Kotlin Coroutines or Java Executors

## Installation

##### Gradle

```kotlin
repositories {
    maven(url = "https://maven.dimensional.fun/releases")
}

depenencies {
    implementation("mixtape.oss:kyuso:$VERSION")
}
```

##### Maven

```xml
<repository>
    <id>dimensional-maven-releases</id>
    <name>Dimensional Maven</name>
    <url>https://maven.dimensional.fun/releases</url>
</repository>
  ```

```xml
<dependency>
    <groupId>mixtape.oss</groupId>
    <artifactId>kyuso</artifactId>
    <version>%VERSION%</version>
</dependency>
```

## Usage

##### Coroutine Backend

The coroutine backend uses Kotlin Coroutines to dispatch tasks.

```kotlin
val kyuso = Kyuso(Dispatchers.Default)
```

##### Executor Backend

The executor backend uses Java Executors to dispatch tasks.

```kotlin
val kyuso = Kyuso(Executors.newSingleThreadExecutor())
```

### Example

```kotlin
kyuso.dispatch {
    println("hello")
}

// executes every 1 second with no initial delay
kyuso.dispatchEvery(1.seconds, null) {
println("Hello World")
}

delay(5.seconds)
```

---

Licensed under the **Apache License** &bull; Read more [here](./LICENSE)
