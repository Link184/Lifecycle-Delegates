![logo](https://github.com/Link184/Lifecycle-Delegates/blob/main/header.png)

Kotlin lifecycle delegates
---

An light library to write android lifecycle aware fields. An comfortable way to avoid `lateinit`
fields and lifecycle methods overriding in android components (fragments, activities).

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.link184/lifecycle-delegates/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.link184/lifecycle-delegates)
[![Build Status](https://travis-ci.com/Link184/Lifecycle-Delegates.svg?branch=main)](https://travis-ci.com/Link184/Lifecycle-Delegates)

```gradle
    implementation 'com.link184:lifecycle-delegates:1.1.0'
```

#### Lifecycle aware initializers:
```kotlin
    private val onCreateInitializedString by onCreate { "Hello from onCreate()" }

    private val onStartInitializedString by onStart { "Hello from onStart()" }

    // initOnlyOnce - when false the field will be re-initialized on each onStart() event
    private val onResumeInitializedString by onStart(initOnlyOnce = false) { "Hello from onResume()" }
```

#### Lifecycle lazy releasable:

Note: it behaves the same as `kotlin.lazy()` which means that all callbacks will NOT be called
until the field is not called for the first time.
```kotlin
    // pausablePlayer will be initialized when the field will be called for first time, the same as kotlin.lazy()
    // onPause callback will be ignored in case pausablePlayer would not have been used before
    private val pausablePlayer by lazyPausable({
        VideoPlayer.create(...)
    }, onPause = {
        it.pause()
    })

    private val pausableResumablePlayer by lazyPausableResumable({
        VideoPlayer.create(...).also { it.play() }
    }, onPause = {
        it.pause()
    }, onResume = {
        it.play()
    })

    private val resumeableField by lazyResumeable(...)
    private val startableField by lazyStartable(...)
    private val stoppableField by lazyStoppable(...)
    private val stoppableStartableField by lazyStoppableStartable(...)
    private val destroyableField by lazyDestroyable(...)
    private val creatableField by lazyCreatable(...)
    private val creatableDestroyableField by lazyCreatableDestroyable(...)
```

#### Lifecycle non-lazy releasable:

The same as lazy releasable delegates without `kotlin.lazy` behaviour

```kotlin
    private val pausableString by pausable(VideoPlayer.create(...), {
        it.pause
    })

    private val pausableResumablePlayer by pausableResumable(VideoPlayer.create(...), onPause = {
        it.pause()
    }, onResume = {
        it.play()
    })

    private val resumeableField by resumeable(...)
    private val startableField by startable(...)
    private val stoppableField by stoppable(...)
    private val stoppableStartableField by stoppableStartable(...)
    private val destroyableField by destroyable(...)
    private val creatableField by creatable(...)
    private val creatableDestroyableField by creatableDestroyable(...)
```

#### Own more custom releasable delegate implementation:
```kotlin
    // lazy
    val customLazyReleasablePlayer by LifecycleLazyReleasableDelegate<Player>({ Player.create(context) },
        Lifecycle.Event.ON_CREATE to { it.init(context) },
        Lifecycle.Event.ON_DESTROY to { it.release() },
        Lifecycle.Event.ON_RESUME to { it.resume() },
        Lifecycle.Event.ON_START to { it.start() },
        ...
    )

    // non-lazy
    val customLazyReleasablePlayer by LifecycleReleasableDelegate<Player>(Player.create(),
        Lifecycle.Event.ON_CREATE to { it.init(context) },
        Lifecycle.Event.ON_DESTROY to { it.release() },
        Lifecycle.Event.ON_RESUME to { it.resume() },
        Lifecycle.Event.ON_START to { it.start() },
        ...
    )
```

License
-------
See the [LICENSE][1] file for details.

[1]: https://github.com/Link184/Lifecycle-Delegates/blob/main/LICENSE
