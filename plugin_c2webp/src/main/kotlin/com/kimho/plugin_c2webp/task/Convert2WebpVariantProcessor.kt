package com.kimho.plugin_c2webp.task

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.google.auto.service.AutoService
import com.kimho.plugin_c2webp.spi.VariantProcessor

/**
 * @Description:
 * @Author: chenjinhong
 * @Date: 2021/2/2
 */

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