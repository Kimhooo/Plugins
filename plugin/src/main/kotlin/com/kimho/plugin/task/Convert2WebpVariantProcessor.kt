package com.kimho.plugin.task

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.kimho.plugin.spi.VariantProcessor
import com.google.auto.service.AutoService


@AutoService(VariantProcessor::class)
class Convert2WebpVariantProcessor : VariantProcessor {

    override fun process(variant: BaseVariant) {

        val variantData = (variant as ApplicationVariantImpl).variantData
        val tasks = variantData.scope.globalScope.project.tasks
        val convert2WebpTask = tasks.findByName("convert2Webp") ?: tasks.create(
            "convert2Webp",
            Convert2WebpTask::class.java
        )
        val mergeResourcesTask = variant.mergeResourcesProvider.get()
        mergeResourcesTask.dependsOn(convert2WebpTask)
    }
}