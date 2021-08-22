package com.k4zy.timetrace

import org.gradle.BuildResult

interface Output {
    fun flush(taskResults: List<TaskResult>, buildResult: BuildResult)
}
