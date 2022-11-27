package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onCreate the callback which will be invoked when [Lifecycle.Event.ON_CREATE] event will occur.
 */
fun <T> LifecycleOwner.creatable(value: T, onCreate: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_CREATE to onCreate)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onDestroy the callback which will be invoked when [Lifecycle.Event.ON_DESTROY] event will occur.
 */
fun <T> LifecycleOwner.destroyable(value: T, onDestroy: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_DESTROY to onDestroy)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onCreate the callback which will be invoked when [Lifecycle.Event.ON_CREATE] event will occur.
 * @param onDestroy the callback which will be invoked when [Lifecycle.Event.ON_DESTROY] event will occur.
 */
fun <T> LifecycleOwner.creatableDestroyable(value: T, onCreate: LifecycleEventAction<T>, onDestroy: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_CREATE to onCreate, Lifecycle.Event.ON_DESTROY to onDestroy)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onPause the callback which will be invoked when [Lifecycle.Event.ON_PAUSE] event will occur.
 */
fun <T> LifecycleOwner.pausable(value: T, onPause: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_PAUSE to onPause)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onResume the callback which will be invoked when [Lifecycle.Event.ON_RESUME] event will occur.
 */
fun <T> LifecycleOwner.resumeable(value: T, onResume: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_RESUME to onResume)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onPause the callback which will be invoked when [Lifecycle.Event.ON_PAUSE] event will occur.
 * @param onResume the callback which will be invoked when [Lifecycle.Event.ON_RESUME] event will occur.
 */
fun <T> LifecycleOwner.pausableResumable(value: T, onPause: LifecycleEventAction<T>, onResume: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_PAUSE to onPause, Lifecycle.Event.ON_RESUME to onResume)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onStart the callback which will be invoked when [Lifecycle.Event.ON_START] event will occur.
 */
fun <T> LifecycleOwner.startable(value: T, onStart: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_START to onStart)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onStop the callback which will be invoked when [Lifecycle.Event.ON_STOP] event will occur.
 */
fun <T> LifecycleOwner.stoppable(value: T, onStop: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_STOP to onStop)
}

/**
 * Creates an delegated property which will be initiated in holder constructor.
 * @param value the value in fact
 * @param onStart the callback which will be invoked when [Lifecycle.Event.ON_START] event will occur.
 * @param onStop the callback which will be invoked when [Lifecycle.Event.ON_STOP] event will occur.
 */
fun <T> LifecycleOwner.stoppableStartable(value: T, onStart: LifecycleEventAction<T>, onStop: LifecycleEventAction<T>): LifecycleReleasableDelegate<T> {
    return LifecycleReleasableDelegate(lifecycle, value, Lifecycle.Event.ON_START to onStart, Lifecycle.Event.ON_STOP to onStop)
}
