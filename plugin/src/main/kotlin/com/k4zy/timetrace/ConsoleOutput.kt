package com.k4zy.timetrace

import org.gradle.BuildResult

class ConsoleOutput : Output {
    override fun flush(taskResults: List<TaskResult>, buildResult: BuildResult) {
        println("taskResults")
        taskResults.forEach { taskResult ->
            println("task:${taskResult.taskPath}: ${taskResult.executionTime}")
        }
        println("Console Output")
    }
}
