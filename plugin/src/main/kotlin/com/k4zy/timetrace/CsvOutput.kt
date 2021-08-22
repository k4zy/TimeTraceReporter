package com.k4zy.timetrace

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import org.gradle.BuildResult

class CsvOutput : Output {
    override fun flush(taskResults: List<TaskResult>, buildResult: BuildResult) {
        taskResults.map {
            listOf(it.executionTime.toString(), it.taskPath)
        }.let { rows ->
            csvWriter().writeAll(rows, "test.csv")
        }
    }
}
