package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
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

@RunWith(JUnit4::class)
class LifecycleLazyReleasableDelegateTest : LifecycleOwner by TestLifecycleOwner {
    private val lifecycleRegistry = lifecycle as LifecycleRegistry

    private var currentState: String = ""
    private val pauseableString by pauseable({
        INITIAL_STRING
    }, {
        currentState = PAUSED_STATE
    })

    @Test
    fun `test pauseable behavior`() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        assertEquals(INITIAL_STRING, pauseableString)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)

        assertEquals(PAUSED_STATE, currentState)
    }

    private val pauseableResumableString by pauseableResumable({
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

    private val resumeableString by resumeable({ INITIAL_STRING }, {
        currentState = RESUMED_STATE
    })

    @Test
    fun `test resumeable behavior`() {
        assertEquals(INITIAL_STRING, resumeableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(RESUMED_STATE, currentState)
    }

    private val startableString by startable({ INITIAL_STRING }, {
        currentState = STARTED_STATE
    })

    @Test
    fun `test startable behavior`() {
        assertEquals(INITIAL_STRING, startableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(STARTED_STATE, currentState)
    }

    private val stoppableString by stoppable({ INITIAL_STRING }, {
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

    private val stoppableStartableString by stoppableStartable({ INITIAL_STRING }, {
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

    private val destroyableString by destroyable({ INITIAL_STRING }, {
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
}