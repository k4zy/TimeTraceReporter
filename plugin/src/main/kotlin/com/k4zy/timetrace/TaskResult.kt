package com.k4zy.timetrace

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

data class TaskResult(
    val executionTime: Long,
    val taskPath: String,
    val didWork: Boolean,
) {
    fun toCsvRow(idx: Int, sysInfo: SysInfo): List<String> {
        val localDateTime = LocalDateTime.now()
        return listOf(
            localDateTime.toEpochSecond(ZoneOffset.UTC).toString(),
            idx.toString(),
            taskPath,
            true.toString(),
            didWork.toString(),
            (!didWork).toString(),
            executionTime.toString(),
            formatter.format(localDateTime),
            sysInfo.cpuIdentifier(),
            sysInfo.maxMemory().toString(),
            sysInfo.osIdentifier()
        )
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss,SSS'Z'")
    }
}
