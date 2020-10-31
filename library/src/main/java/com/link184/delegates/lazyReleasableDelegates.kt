package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

fun <T> LifecycleOwner.lazyCreatable(initializer: LazyInitializer<T>, onCreate: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_CREATE to onCreate)
}

fun <T> LifecycleOwner.lazyDestroyable(initializer: LazyInitializer<T>, onDestroy: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_DESTROY to onDestroy)
}

fun <T> LifecycleOwner.lazyCreatableDestroyable(initializer: LazyInitializer<T>, onCreate: LifecycleEventAction<T>, onDestroy: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_CREATE to onCreate, Lifecycle.Event.ON_DESTROY to onDestroy)
}

fun <T> LifecycleOwner.lazyPauseable(initializer: LazyInitializer<T>, onPause: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_PAUSE to onPause)
}

fun <T> LifecycleOwner.lazyResumeable(initializer: LazyInitializer<T>, onResume: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_RESUME to onResume)
}

fun <T> LifecycleOwner.lazyPauseableResumable(initializer: LazyInitializer<T>, onPause: LifecycleEventAction<T>, onResume: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_PAUSE to onPause, Lifecycle.Event.ON_RESUME to onResume)
}

fun <T> LifecycleOwner.lazyStartable(initializer: LazyInitializer<T>, onStart: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_START to onStart)
}

fun <T> LifecycleOwner.lazyStoppable(initializer: LazyInitializer<T>, onStop: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_STOP to onStop)
}

fun <T> LifecycleOwner.lazyStoppableStartable(initializer: LazyInitializer<T>, onStart: LifecycleEventAction<T>, onStop: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_START to onStart, Lifecycle.Event.ON_STOP to onStop)
}