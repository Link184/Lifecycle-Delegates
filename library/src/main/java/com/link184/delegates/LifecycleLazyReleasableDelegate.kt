package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias LazyInitializer<T> = () -> T
typealias LifecycleEventAction<T> = (T) -> Unit

/**
 * An field delegate to control the field initialization and resource release/close behavior.
 * @param initializer the function which will initialize the field. The function will be called in
 * a lazy form according to [kotlin.lazy] behavior.
 * @param actions an vararg of pairs of [Lifecycle.Event] associated to functions witch will
 * be triggered when [Lifecycle.Event] will be triggered on the delegated field owner.
 */
class LifecycleLazyReleasableDelegate<T>(
    initializer: LazyInitializer<T>,
    private vararg val actions: Pair<Lifecycle.Event, LifecycleEventAction<T>>
) : ReadOnlyProperty<LifecycleOwner, T>, LifecycleEventObserver {
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