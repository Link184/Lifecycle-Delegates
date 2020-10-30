package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LifecycleDelegate<T>(
        private val initializer: () -> T,
        vararg val actions: Pair<Lifecycle.Event, (T) -> Unit>
): ReadOnlyProperty<LifecycleOwner, T>, LifecycleEventObserver {
    private val value: T by lazy { initializer() }

    override fun getValue(thisRef: LifecycleOwner, property: KProperty<*>): T {
        thisRef.lifecycle.addObserver(this)
        return value
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        actions.firstOrNull {
            it.first == event
        }
                ?.second?.invoke(value)
    }
}