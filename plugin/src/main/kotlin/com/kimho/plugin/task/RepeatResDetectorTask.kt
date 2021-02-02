package com.kimho.plugin.task

import com.kimho.plugin.util.encode
import com.kimho.plugin.util.writeToJson
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File


internal open class RepeatResDetectorTask : DefaultTask() {

    @TaskAction
    fun run() {
        println(
            """
                *********************************************
                ******** -- RepeatResDetectorTask -- ********
                ****** -- projectDir/repeatRes.json -- ******
                *********************************************
            """.trimIndent()
        )

        val map = HashMap<String, List<String>>()
        val prefix = if (project.properties["all"] != "true") "drawable-" else "drawable"
        project.projectDir.resolve("src/main/res").listFiles()?.filter {
            it.name.startsWith(prefix)
        }?.forEach { dir ->
            if (dir.isDirectory) {
                dir.listFiles()?.forEach { file ->
                    val key = file.readBytes().encode()
                    val value = arrayListOf<String>()
                    val list = map[key]
                    list?.let { it1 -> value.addAll(it1) }
                    value.add(file.absolutePath)
                    map[key] = value
                }
            }
        }
        var length: Long = 0
        map.filterValues { values ->
            values.size > 1
        }.apply {
            this.values.forEach {
                length += File(it[0]).length()
            }
            println("Repeat Res size: ${length / 1000}kb ")

            this.writeToJson("${project.parent?.projectDir}/repeatRes.json")
        }
    }
}