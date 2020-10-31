package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LifecycleInitializationDelegate<T>(
        private val initEvent: Lifecycle.Event,
        private val initOnlyOnce: Boolean = true,
        private val initializer: LazyInitializer<T>
): ReadOnlyProperty<LifecycleOwner, T>, LifecycleEventObserver {
    private var value: T? = null

    override fun getValue(thisRef: LifecycleOwner, property: KProperty<*>): T {
        thisRef.lifecycle.addObserver(this)
        return value ?: throw IllegalStateException("The field was not initialized yet, try to access it after $initEvent")
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when {
            !initOnlyOnce && event == initEvent -> {
                value = initializer()
                return
            }
            value == null && event == initEvent -> {
                value = initializer()
            }
        }
    }
}