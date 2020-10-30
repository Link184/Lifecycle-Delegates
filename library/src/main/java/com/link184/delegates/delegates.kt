package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

fun <T> LifecycleOwner.destroyable(initializer: () -> T, onDestroy: (T) -> Unit): LifecycleDelegate<T> {
    return LifecycleDelegate(initializer, Lifecycle.Event.ON_DESTROY to onDestroy)
}

fun <T> LifecycleOwner.pauseable(initializer: () -> T, onPause: (T) -> Unit) : LifecycleDelegate<T> {
    return LifecycleDelegate(initializer, Lifecycle.Event.ON_PAUSE to onPause)
}

fun <T> LifecycleOwner.resumeable(initializer: () -> T, onResume: (T) -> Unit) : LifecycleDelegate<T> {
    return LifecycleDelegate(initializer, Lifecycle.Event.ON_RESUME to onResume)
}

fun <T> LifecycleOwner.pauseableResumable(initializer: () -> T, onPause: (T) -> Unit, onResume: (T) -> Unit): LifecycleDelegate<T> {
    return LifecycleDelegate(initializer, Lifecycle.Event.ON_PAUSE to onPause, Lifecycle.Event.ON_RESUME to onResume)
}

fun <T> LifecycleOwner.startable(initializer: () -> T, onStart: (T) -> Unit) : LifecycleDelegate<T> {
    return LifecycleDelegate(initializer, Lifecycle.Event.ON_START to onStart)
}

fun <T> LifecycleOwner.stopable(initializer: () -> T, onStop: (T) -> Unit) : LifecycleDelegate<T> {
    return LifecycleDelegate(initializer, Lifecycle.Event.ON_STOP to onStop)
}

fun <T> LifecycleOwner.stopableStartable(initializer: () -> T, onStart: (T) -> Unit, onStop: (T) -> Unit) : LifecycleDelegate<T> {
    return LifecycleDelegate(initializer, Lifecycle.Event.ON_START to onStart, Lifecycle.Event.ON_STOP to onStop)
}