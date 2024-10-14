package com.example.smartfinancemanagementapp.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.getOrAwaitValue(): T {
    var data: T? = null
    val latch = java.util.concurrent.CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    latch.await(2, java.util.concurrent.TimeUnit.SECONDS)
    return data ?: throw KotlinNullPointerException("LiveData content is empty!")
}
