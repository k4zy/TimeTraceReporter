package com.k4zy.timetrace

class Clock {
    private val startMills: Long = System.currentTimeMillis()

    fun measureTimeMills(): Long {
        return System.currentTimeMillis() - startMills
    }
}
