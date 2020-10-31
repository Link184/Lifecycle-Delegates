package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

fun <T> LifecycleOwner.destroyable(initializer: LazyInitializer<T>, onDestroy: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(initializer, Lifecycle.Event.ON_DESTROY to onDestroy)
}

fun <T> LifecycleOwner.pauseable(initializer: LazyInitializer<T>, onPause: LifecycleEventAction<T>) : LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(initializer, Lifecycle.Event.ON_PAUSE to onPause)
}

fun <T> LifecycleOwner.resumeable(initializer: LazyInitializer<T>, onResume: LifecycleEventAction<T>) : LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(initializer, Lifecycle.Event.ON_RESUME to onResume)
}

fun <T> LifecycleOwner.pauseableResumable(initializer: LazyInitializer<T>, onPause: LifecycleEventAction<T>, onResume: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(initializer, Lifecycle.Event.ON_PAUSE to onPause, Lifecycle.Event.ON_RESUME to onResume)
}

fun <T> LifecycleOwner.startable(initializer: LazyInitializer<T>, onStart: LifecycleEventAction<T>) : LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(initializer, Lifecycle.Event.ON_START to onStart)
}

fun <T> LifecycleOwner.stoppable(initializer: LazyInitializer<T>, onStop: LifecycleEventAction<T>) : LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(initializer, Lifecycle.Event.ON_STOP to onStop)
}

fun <T> LifecycleOwner.stoppableStartable(initializer: LazyInitializer<T>, onStart: LifecycleEventAction<T>, onStop: LifecycleEventAction<T>) : LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(initializer, Lifecycle.Event.ON_START to onStart, Lifecycle.Event.ON_STOP to onStop)
}