package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 * @see kotlin.lazy

 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onCreate the callback which will be invoked when [Lifecycle.Event.ON_CREATE]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_CREATE] events. It behaves the same as [kotlin.lazy]
 */
fun <T> LifecycleOwner.lazyCreatable(initializer: LazyInitializer<T>, onCreate: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_CREATE to onCreate)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 * @see kotlin.lazy
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onDestroy the callback which will be invoked when [Lifecycle.Event.ON_DESTROY]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_DESTROY] events. It behaves the same as [kotlin.lazy]
 */

fun <T> LifecycleOwner.lazyDestroyable(initializer: LazyInitializer<T>, onDestroy: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_DESTROY to onDestroy)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 * @see kotlin.lazy
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onCreate the callback which will be invoked when [Lifecycle.Event.ON_CREATE]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_CREATE] events. It behaves the same as [kotlin.lazy]
 * @param onDestroy the callback which will be invoked when [Lifecycle.Event.ON_DESTROY]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_DESTROY] events. It behaves the same as [kotlin.lazy]
 */

fun <T> LifecycleOwner.lazyCreatableDestroyable(initializer: LazyInitializer<T>, onCreate: LifecycleEventAction<T>, onDestroy: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_CREATE to onCreate, Lifecycle.Event.ON_DESTROY to onDestroy)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 * @see kotlin.lazy
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onPause the callback which will be invoked when [Lifecycle.Event.ON_PAUSE]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_PAUSE] events. It behaves the same as [kotlin.lazy]
 */
fun <T> LifecycleOwner.lazyPausable(initializer: LazyInitializer<T>, onPause: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_PAUSE to onPause)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 * @see kotlin.lazy
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onResume the callback which will be invoked when [Lifecycle.Event.ON_RESUME]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_RESUME] events. It behaves the same as [kotlin.lazy]
 */
fun <T> LifecycleOwner.lazyResumeable(initializer: LazyInitializer<T>, onResume: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_RESUME to onResume)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 * @see kotlin.lazy
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onPause the callback which will be invoked when [Lifecycle.Event.ON_PAUSE]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_PAUSE] events. It behaves the same as [kotlin.lazy]
 * @param onResume the callback which will be invoked when [Lifecycle.Event.ON_RESUME]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_RESUME] events. It behaves the same as [kotlin.lazy]
 */
fun <T> LifecycleOwner.lazyPausableResumable(initializer: LazyInitializer<T>, onPause: LifecycleEventAction<T>, onResume: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_PAUSE to onPause, Lifecycle.Event.ON_RESUME to onResume)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 * @see kotlin.lazy
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onStart the callback which will be invoked when [Lifecycle.Event.ON_START]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_START] events. It behaves the same as [kotlin.lazy]
 */
fun <T> LifecycleOwner.lazyStartable(initializer: LazyInitializer<T>, onStart: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_START to onStart)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onStop the callback which will be invoked when [Lifecycle.Event.ON_STOP]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_STOP] events. It behaves the same as [kotlin.lazy]
 */
fun <T> LifecycleOwner.lazyStoppable(initializer: LazyInitializer<T>, onStop: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_STOP to onStop)
}

/**
 * Creates an delegated property which will be initiated at first call.
 *
 * That delegated field have the same behavior as [kotlin.lazy]. Which means the lifecycle callbacks
 * will NOT be called if the field will not be called before in your logic,
 * just because the field will be uninitialized.
 *
 * @param initializer the initialization callback which will be called when the field will
 * be called for the first time.
 * @param onStart the callback which will be invoked when [Lifecycle.Event.ON_START]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_START] events. It behaves the same as [kotlin.lazy]
 * @param onStop the callback which will be invoked when [Lifecycle.Event.ON_STOP]
 * event will occur. The callback will NOT be called if the field was not called before
 * [Lifecycle.Event.ON_STOP] events. It behaves the same as [kotlin.lazy]
 */
fun <T> LifecycleOwner.lazyStoppableStartable(initializer: LazyInitializer<T>, onStart: LifecycleEventAction<T>, onStop: LifecycleEventAction<T>): LifecycleLazyReleasableDelegate<T> {
    return LifecycleLazyReleasableDelegate(lifecycle, initializer, Lifecycle.Event.ON_START to onStart, Lifecycle.Event.ON_STOP to onStop)
}
