package com.kimho.plugin.task

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.kimho.plugin.spi.VariantProcessor
import com.google.auto.service.AutoService


@AutoService(VariantProcessor::class)
class RepeatResDetectorVariantProcessor : VariantProcessor {
    override fun process(variant: BaseVariant) {
        val variantData = (variant as ApplicationVariantImpl).variantData
        val tasks = variantData.scope.globalScope.project.tasks
        tasks.findByName("repeatRes") ?: tasks.create(
            "repeatRes",
            RepeatResDetectorTask::class.java
        )
    }
}