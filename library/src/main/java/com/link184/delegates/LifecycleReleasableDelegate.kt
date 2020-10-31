package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * An field delegate to control the field initialization and resource release/close behavior.
 * @param value the value of that field.
 * @param actions an vararg of pairs of [Lifecycle.Event] associated to functions witch will
 * be triggered when [Lifecycle.Event] will be triggered on the delegated field owner.
 */
class LifecycleReleasableDelegate<T>(
    lifecycle: Lifecycle,
    private val value: T,
    private vararg val actions: Pair<Lifecycle.Event, LifecycleEventAction<T>>
) : ReadOnlyProperty<LifecycleOwner, T>, LifecycleEventObserver {
    init {
        lifecycle.addObserver(this)
    }

    override fun getValue(thisRef: LifecycleOwner, property: KProperty<*>): T {
        thisRef.lifecycle.addObserver(this)
        return value
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        actions.find {
            it.first == event
        }
                ?.second?.invoke(value)
    }
}