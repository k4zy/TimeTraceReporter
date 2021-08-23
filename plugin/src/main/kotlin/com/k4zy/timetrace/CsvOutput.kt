package com.k4zy.timetrace

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import org.gradle.BuildResult

class CsvOutput : Output {
    private val headers = listOf(
        "timestamp", "order", "task", "success", "did_work", "skipped", "ms", "date",
        "cpu", "memory", "os"
    )

    override fun flush(taskResults: List<TaskResult>, buildResult: BuildResult) {
        println("Csv Output")
        val sysInfo = SysInfo()
        taskResults.mapIndexed { index, taskResult ->
            println(taskResult)
            taskResult.toCsvRow(index, sysInfo)
        }.let { rows ->
            csvWriter().writeAll(rows, "test.csv")
        }
    }
}
