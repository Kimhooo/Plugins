package com.kimho.plugin_c2webp

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.kimho.plugin_c2webp.spi.VariantProcessor
import com.kimho.ext.Convert2WebpExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

/**
 * @Description:
 * @Author: chenjinhong
 * @Date: 2021/2/2
 */
class c2webpPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        println("apply plugin: 'com.kimho.plugin_c2webp'")

//        project.repositories.maven {
//            it.url = URI("http://192.168.9.230:8081/repository/app-releases/")
//        }

        // Extension
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