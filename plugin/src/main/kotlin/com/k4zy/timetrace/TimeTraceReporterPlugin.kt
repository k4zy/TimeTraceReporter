/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.k4zy.timetrace

import org.gradle.api.Project
import org.gradle.api.Plugin

class TimeTraceReporterPlugin : Plugin<Project> {
    val outputs: MutableList<Output> = mutableListOf(ConsoleOutput())
    override fun apply(project: Project) {
        project.extensions.create(
            TimeTraceReporterExtension.NAME,
            TimeTraceReporterExtension::class.java
        )
        val ext = project.extensions.getByType(TimeTraceReporterExtension::class.java)
        if (ext.csvReport) {
            outputs.add(CsvOutput())
        }
        project.gradle.addBuildListener(BuildTimeListener(this))
    }
}
