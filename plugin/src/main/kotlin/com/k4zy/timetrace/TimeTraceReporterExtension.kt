package com.k4zy.timetrace

open class TimeTraceReporterExtension {
    var enableCsvOutput: Boolean = true
    var enableConsoleOutput: Boolean = false
    var csvHeader: Boolean = false
    var csvFileName: String = "build-time-report.csv"

    companion object {
        const val NAME = "timeTraceReporter"
    }
}
