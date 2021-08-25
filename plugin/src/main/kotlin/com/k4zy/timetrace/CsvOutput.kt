package com.k4zy.timetrace

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import org.gradle.BuildResult
import java.time.LocalDateTime

class CsvOutput(private val extension: TimeTraceReporterExtension) : Output {
    private val headers = listOf(
        "timestamp", "order", "task", "success", "did_work", "skipped", "ms", "date",
        "cpu", "memory", "os"
    )

    override fun flush(taskResults: List<TaskResult>, buildResult: BuildResult) {
        if (!extension.enableCsvOutput) {
            return
        }
        val sysInfo = SysInfo()
        val localDateTime = LocalDateTime.now()
        val rows = taskResults.mapIndexed { index, taskResult ->
            taskResult.toCsvRow(index, localDateTime, sysInfo)
        }
        val csv = if (extension.csvHeader) {
            listOf(headers) + rows
        } else {
            rows
        }
        csvWriter().writeAll(csv, extension.csvFileName)
    }
}
