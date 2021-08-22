package com.k4zy.timetrace

import org.gradle.BuildAdapter
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.tasks.TaskState

class BuildTimeListener(private val plugin: TimeTraceReporterPlugin) : TaskExecutionListener,
    BuildAdapter() {
    private var clock: Clock? = null

    private val taskResults: MutableList<TaskResult> = mutableListOf()

    override fun beforeExecute(task: Task) {
        clock = Clock()
    }

    override fun afterExecute(task: Task, taskState: TaskState) {
        val executionTime = clock?.measureTimeMills() ?: 0L
        val taskResult = TaskResult(
            executionTime = executionTime,
            taskPath = task.path,
            didWork = task.didWork,
        )
        taskResults.add(taskResult)
    }

    override fun buildFinished(result: BuildResult) {
        super.buildFinished(result)
        plugin.outputs.forEach { output ->
            output.flush(taskResults, result)
        }
    }
}
