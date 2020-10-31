package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * An delegate to control filed initialization at a specific android lifecycle event.
 * @param initEvent the event when to init the field
 * @param initOnlyOnce when true the field will be initialized only once. When false and
 * the same event will be triggered the field will be re-initialized by [initializer]
 * @param initializer the function which will initialize the field.
 */
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