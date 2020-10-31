package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias LazyInitializer<T> = () -> T
typealias LifecycleEventAction<T> = (T) -> Unit

class LifecycleLazyReleasableDelegate<T>(
        initializer: () -> T,
        private vararg val actions: Pair<Lifecycle.Event, LifecycleEventAction<T>>
): ReadOnlyProperty<LifecycleOwner, T>, LifecycleEventObserver {
    private var value: Lazy<T> = lazy(initializer)

    override fun getValue(thisRef: LifecycleOwner, property: KProperty<*>): T {
        thisRef.lifecycle.addObserver(this)
        return value.value
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (!value.isInitialized()) {
            return
        }

        actions.firstOrNull {
            it.first == event
        }
                ?.second?.invoke(value.value)
    }
}