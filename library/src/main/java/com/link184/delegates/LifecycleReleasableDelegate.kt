package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LifecycleReleasableDelegate<T>(
        private val value: T,
        private vararg val actions: Pair<Lifecycle.Event, LifecycleEventAction<T>>
): ReadOnlyProperty<LifecycleOwner, T>, LifecycleEventObserver {

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