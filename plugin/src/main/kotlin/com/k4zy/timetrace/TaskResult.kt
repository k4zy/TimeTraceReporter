package com.k4zy.timetrace

data class TaskResult(
    val executionTime: Long,
    val taskPath: String,
    val didWork: Boolean,
)
