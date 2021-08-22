package com.k4zy.timetrace

import com.sun.management.OperatingSystemMXBean
import java.io.File
import java.lang.management.ManagementFactory
import java.util.concurrent.TimeUnit

class SysInfo {
    fun osIdentifier(): String {
        return listOf("os.name", "os.version", "os.arch")
            .map { System.getProperty(it) }
            .joinToString(" ")
    }

    fun maxMemory(): Long {
        val bean = ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean
        return bean.totalPhysicalMemorySize
    }

    fun cpuIdentifier(): String {
        val os = System.getProperty("os.name")
        if (os.equals("mac os x", ignoreCase = true)) {
            val parts = listOf("sysctl", "-n", "machdep.cpu.brand_string")
            val proc = ProcessBuilder(*parts.toTypedArray())
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()
            proc.waitFor(60, TimeUnit.SECONDS)
            if (proc.exitValue() == 0) {
                return proc.inputStream.bufferedReader().readText().trim()
            }
        } else if (os.equals("linux", ignoreCase = true)) {
            var osName = ""
            File("/proc/cpuinfo").forEachLine {
                if (!osName.isEmpty()) return@forEachLine

                if (it.startsWith("model name")) {
                    osName = it.split(": ")[1]
                }
            }
            return osName
        }

        return ""
    }
}
