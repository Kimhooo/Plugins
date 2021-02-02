package com.kimho.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.kimho.plugin.ext.Convert2WebpExtension
import com.kimho.plugin.spi.VariantProcessor
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*


class c2webpPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        println("apply plugin: 'com.kimho.c2webpPlugin'")


        project.extensions.create("convert2WebpConfig", Convert2WebpExtension::class.java)

        when {
            project.plugins.hasPlugin("com.android.application") -> project.extensions.getByType(
                AppExtension::class.java
            ).let { android ->
                project.afterEvaluate {
                    ServiceLoader.load(VariantProcessor::class.java, javaClass.classLoader)
                        .toList().let { processes ->
                            android.applicationVariants.forEach { variant ->
                                processes.forEach {
                                    it.process(variant)
                                }
                            }
                        }
                }
            }

            project.plugins.hasPlugin("com.android.library") -> project.extensions.getByType(
                LibraryExtension::class.java
            ).let { android ->
                project.afterEvaluate {
                    ServiceLoader.load(VariantProcessor::class.java, javaClass.classLoader)
                        .toList().let { processes ->
                            android.libraryVariants.forEach { variant ->
                                processes.forEach {
                                    it.process(variant)
                                }
                            }
                        }

                }
            }
        }
    }
}