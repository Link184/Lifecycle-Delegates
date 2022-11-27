package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.testing.TestLifecycleOwner
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

private const val INITIAL_STRING = "initial string"

private const val PAUSED_STATE = "paused"
private const val RESUMED_STATE = "resumed"
private const val STARTED_STATE = "started"
private const val STOPPED_STATE = "stopped"
private const val DESTROYED_STATE = "destroy"
private const val CREATED_STATE = "created"

@RunWith(JUnit4::class)
class LifecycleLazyReleasableDelegateTest : LifecycleOwner by TestLifecycleOwner(initialState = Lifecycle.State.INITIALIZED) {
    private val lifecycleRegistry = lifecycle as LifecycleRegistry

    private var currentState: String = ""
    private val pauseableString by lazyPauseable({
        INITIAL_STRING
    }, {
        currentState = PAUSED_STATE
    })

    @After
    fun tearDown() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    @Test
    fun `test pauseable behavior`() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        assertEquals(INITIAL_STRING, pauseableString)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)

        assertEquals(PAUSED_STATE, currentState)
    }

    private val pauseableResumableString by lazyPauseableResumable({
        INITIAL_STRING
    }, {
        currentState = PAUSED_STATE
    }, {
        currentState = RESUMED_STATE
    })

    @Test
    fun `test pauseable resumable behavior`() {
        assertEquals(INITIAL_STRING, pauseableResumableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(RESUMED_STATE, currentState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        assertEquals(PAUSED_STATE, currentState)
    }

    private val resumeableString by lazyResumeable({ INITIAL_STRING }, {
        currentState = RESUMED_STATE
    })

    @Test
    fun `test resumeable behavior`() {
        assertEquals(INITIAL_STRING, resumeableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(RESUMED_STATE, currentState)
    }

    private val startableString by lazyStartable({ INITIAL_STRING }, {
        currentState = STARTED_STATE
    })

    @Test
    fun `test startable behavior`() {
        assertEquals(INITIAL_STRING, startableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(STARTED_STATE, currentState)
    }

    private val stoppableString by lazyStoppable({ INITIAL_STRING }, {
        currentState = STOPPED_STATE
    })

    @Test
    fun `test stoppable behavior`() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(INITIAL_STRING, stoppableString)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        assertEquals(STOPPED_STATE, currentState)
    }

    private val stoppableStartableString by lazyStoppableStartable({ INITIAL_STRING }, {
        currentState = STARTED_STATE
    }, {
        currentState = STOPPED_STATE
    })

    @Test
    fun `test stoppable startable behavior`() {
        assertEquals(INITIAL_STRING, stoppableStartableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(STARTED_STATE, currentState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        assertEquals(STOPPED_STATE, currentState)
    }

    private val destroyableString by lazyDestroyable({ INITIAL_STRING }, {
        currentState = DESTROYED_STATE
    })

    @Test
    fun `test destroyable behavior`() {
        assertEquals(INITIAL_STRING, destroyableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertEquals(DESTROYED_STATE, currentState)
    }

    private val creatableString by lazyCreatable({ INITIAL_STRING }, {
        currentState = CREATED_STATE
    })

    @Test
    fun `test creatable behavior`() {
        assertEquals(INITIAL_STRING, creatableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertEquals(CREATED_STATE, currentState)
    }

    private val creatableDestroyableString by lazyCreatableDestroyable({ INITIAL_STRING }, {
        currentState = CREATED_STATE
    }, {
        currentState = DESTROYED_STATE
    })

    @Test
    fun `test creatable destroyable behavior`() {
        assertEquals(INITIAL_STRING, creatableDestroyableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(CREATED_STATE, currentState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertEquals(DESTROYED_STATE, currentState)
    }
}
