package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class LifecycleInitializationDelegateTest : LifecycleOwner by TestLifecycleOwner {
    private val lifecycleRegistry = lifecycle as LifecycleRegistry
    private var currentState: Lifecycle.Event? = null

    private val onCreateInitializedString by onCreate {
        currentState = Lifecycle.Event.ON_CREATE
        currentState!!.name
    }

    @After
    fun tearDown() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    @Test
    fun `test onCreate initializer`() {
        assertFailsWith<IllegalStateException>("the field call should fall with exception because the field should be uninitialized before corresponding event") {
            onCreateInitializedString
        }

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        assertEquals(Lifecycle.Event.ON_CREATE, currentState)
        assertEquals(Lifecycle.Event.ON_CREATE.name, onCreateInitializedString)
    }

    private val onStartOnlyOnceInitializedString by onStart {
        currentState = Lifecycle.Event.ON_START
        currentState!!.name
    }

    @Test
    fun `test onStart only once initializer`() {
        assertFailsWith<IllegalStateException>("the field call should fall with exception because the field should be uninitialized before corresponding event") {
            onStartOnlyOnceInitializedString
        }

        repeat(10) {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
            assertEquals(Lifecycle.Event.ON_START, currentState)
            assertEquals(Lifecycle.Event.ON_START.name, onStartOnlyOnceInitializedString)
        }
    }

    private var onStartInitializationCount = 0
    private val onStartAlwaysInitializedString by onStart(false) {
        currentState = Lifecycle.Event.ON_START
        onStartInitializationCount++
        currentState!!.name
    }

    @Test
    fun `test onStart always initializer`() {
        assertFailsWith<IllegalStateException>("the field call should fall with exception because the field should be uninitialized before corresponding event") {
            onStartAlwaysInitializedString
        }

        val repeatTimes = 20
        repeat(repeatTimes) {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
            assertEquals(Lifecycle.Event.ON_START, currentState)
            assertEquals(Lifecycle.Event.ON_START.name, onStartAlwaysInitializedString)
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        }
        assertEquals(repeatTimes, onStartInitializationCount)
    }

    private val onResumeOnlyOnceInitializedString by onResume {
        currentState = Lifecycle.Event.ON_RESUME
        currentState!!.name
    }

    @Test
    fun `test onResume only once initializer`() {
        assertFailsWith<IllegalStateException>("the field call should fall with exception because the field should be uninitialized before corresponding event") {
            onResumeOnlyOnceInitializedString
        }

        repeat(10) {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            assertEquals(Lifecycle.Event.ON_RESUME, currentState)
            assertEquals(Lifecycle.Event.ON_RESUME.name, onResumeOnlyOnceInitializedString)
        }
    }

    private var onResumeInitializationCount = 0
    private val onResumeAlwaysInitializedString by onResume(false) {
        currentState = Lifecycle.Event.ON_RESUME
        onResumeInitializationCount++
        currentState!!.name
    }

    @Test
    fun `test onResume always initializer`() {
        assertFailsWith<IllegalStateException>("the field call should fall with exception because the field should be uninitialized before corresponding event") {
            onResumeAlwaysInitializedString
        }

        val repeatTimes = 20
        repeat(repeatTimes) {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            assertEquals(Lifecycle.Event.ON_RESUME, currentState)
            assertEquals(Lifecycle.Event.ON_RESUME.name, onResumeAlwaysInitializedString)
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        }
        assertEquals(repeatTimes, onResumeInitializationCount)
    }
}