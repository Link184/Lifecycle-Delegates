package com.link184.delegates

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

object TestLifecycleOwner : LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycle
}