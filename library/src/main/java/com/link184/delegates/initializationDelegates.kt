@file:Suppress("unused")

package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

fun <T> LifecycleOwner.onCreate(initializer: LazyInitializer<T>): LifecycleInitializationDelegate<T> {
    return LifecycleInitializationDelegate(Lifecycle.Event.ON_CREATE, initializer = initializer)
}

fun <T> LifecycleOwner.onStart(initOnlyOnce: Boolean = true, initializer: LazyInitializer<T>): LifecycleInitializationDelegate<T> {
    return LifecycleInitializationDelegate(Lifecycle.Event.ON_START, initOnlyOnce, initializer)
}

fun <T> LifecycleOwner.onResume(initOnlyOnce: Boolean = true, initializer: LazyInitializer<T>): LifecycleInitializationDelegate<T> {
    return LifecycleInitializationDelegate(Lifecycle.Event.ON_RESUME, initOnlyOnce, initializer)
}