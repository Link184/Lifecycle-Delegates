package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Creates an delegated property which will be initiated when [Lifecycle.Event.ON_CREATE] will occur.
 * The field can be unavailable during [Lifecycle.Event.ON_CREATE] event but for sure it will
 * should be initialized and available in next lifecycle events.
 *
 * @param initializer the initialization callback which will be called right after [Lifecycle.Event.ON_CREATE] event.
 */
fun <T> LifecycleOwner.onCreate(initializer: LazyInitializer<T>): LifecycleInitializationDelegate<T> {
    return LifecycleInitializationDelegate(lifecycle, Lifecycle.Event.ON_CREATE, initializer = initializer)
}

/**
 * Creates an delegated property which will be initiated when [Lifecycle.Event.ON_START] will occur.
 * The field can be unavailable during [Lifecycle.Event.ON_START] event but for sure it will
 * should be initialized and available in next lifecycle events.
 *
 * @param initializer the initialization callback which will be called right after [Lifecycle.Event.ON_START] event.
 */
fun <T> LifecycleOwner.onStart(initOnlyOnce: Boolean = true, initializer: LazyInitializer<T>): LifecycleInitializationDelegate<T> {
    return LifecycleInitializationDelegate(lifecycle, Lifecycle.Event.ON_START, initOnlyOnce, initializer)
}

/**
 * Creates an delegated property which will be initiated when [Lifecycle.Event.ON_RESUME] will occur.
 * The field can be unavailable during [Lifecycle.Event.ON_RESUME] event but for sure it will
 * should be initialized and available in next lifecycle events.
 *
 * @param initializer the initialization callback which will be called right after [Lifecycle.Event.ON_RESUME] event.
 */
fun <T> LifecycleOwner.onResume(initOnlyOnce: Boolean = true, initializer: LazyInitializer<T>): LifecycleInitializationDelegate<T> {
    return LifecycleInitializationDelegate(lifecycle, Lifecycle.Event.ON_RESUME, initOnlyOnce, initializer)
}