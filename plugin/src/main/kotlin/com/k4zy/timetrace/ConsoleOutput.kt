package com.k4zy.timetrace

import org.gradle.BuildResult

class ConsoleOutput(private val extension: TimeTraceReporterExtension) : Output {
    override fun flush(taskResults: List<TaskResult>, buildResult: BuildResult) {
        if (!extension.enableConsoleOutput) {
            return
        }
        taskResults.forEach { taskResult ->
            println("task:${taskResult.taskPath}: ${taskResult.executionTime}")
        }
    }
}
