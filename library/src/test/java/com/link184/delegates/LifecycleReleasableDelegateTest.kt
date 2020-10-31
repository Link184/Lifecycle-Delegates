package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.nhaarman.mockitokotlin2.mock
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
class LifecycleReleasableDelegateTest : LifecycleOwner by TestLifecycleOwner {
    private val lifecycleRegistry = lifecycle as LifecycleRegistry


    @After
    fun tearDown() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    @Test
    fun `test pauseable behavior`() {
        var currentState: String = ""

        val pauseableString = pauseable(INITIAL_STRING, {
            currentState = PAUSED_STATE
        }).getValue(this, mock())

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        assertEquals(INITIAL_STRING, pauseableString)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)

        assertEquals(PAUSED_STATE, currentState)
    }

    @Test
    fun `test pauseable resumable behavior`() {
        var currentState: String = ""

        val pauseableResumableString = pauseableResumable(INITIAL_STRING, {
            currentState = PAUSED_STATE
        }, {
            currentState = RESUMED_STATE
        }).getValue(this, mock())
        assertEquals(INITIAL_STRING, pauseableResumableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(RESUMED_STATE, currentState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        assertEquals(PAUSED_STATE, currentState)
    }

    @Test
    fun `test resumeable behavior`() {
        var currentState: String = ""
        val resumeableString = resumeable(INITIAL_STRING, {
            currentState = RESUMED_STATE
        }).getValue(this, mock())

        assertEquals(INITIAL_STRING, resumeableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(RESUMED_STATE, currentState)
    }

    @Test
    fun `test startable behavior`() {
        var currentState: String = ""
        val startableString = startable(INITIAL_STRING, {
            currentState = STARTED_STATE
        }).getValue(this, mock())

        assertEquals(INITIAL_STRING, startableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(STARTED_STATE, currentState)
    }

    @Test
    fun `test stoppable behavior`() {
        var currentState: String = ""
        val stoppableString = stoppable(INITIAL_STRING, {
            currentState = STOPPED_STATE
        }).getValue(this, mock())

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(INITIAL_STRING, stoppableString)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        assertEquals(STOPPED_STATE, currentState)
    }

    @Test
    fun `test stoppable startable behavior`() {
        var currentState: String = ""
        val stoppableStartableString = stoppableStartable(INITIAL_STRING, {
            currentState = STARTED_STATE
        }, {
            currentState = STOPPED_STATE
        }).getValue(this, mock())

        assertEquals(INITIAL_STRING, stoppableStartableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(STARTED_STATE, currentState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        assertEquals(STOPPED_STATE, currentState)
    }

    @Test
    fun `test destroyable behavior`() {
        var currentState: String = ""
        val destroyableString = destroyable(INITIAL_STRING, {
            currentState = DESTROYED_STATE
        }).getValue(this, mock())

        assertEquals(INITIAL_STRING, destroyableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertEquals(DESTROYED_STATE, currentState)
    }

    @Test
    fun `test creatable behavior`() {
        var currentState: String = ""
        val creatableString = creatable(INITIAL_STRING, {
            currentState = CREATED_STATE
        }).getValue(this, mock())

        assertEquals(INITIAL_STRING, creatableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertEquals(CREATED_STATE, currentState)
    }

    @Test
    fun `test creatable destroyable behavior`() {
        var currentState: String = ""
        val creatableDestroyableString = creatableDestroyable(INITIAL_STRING, {
            currentState = CREATED_STATE
        }, {
            currentState = DESTROYED_STATE
        }).getValue(this, mock())

        assertEquals(INITIAL_STRING, creatableDestroyableString)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        assertEquals(CREATED_STATE, currentState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertEquals(DESTROYED_STATE, currentState)
    }
}