package com.kimho.plugin.task

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.kimho.plugin.spi.VariantProcessor
import com.google.auto.service.AutoService


@AutoService(VariantProcessor::class)
class ListPermissionVariantProcessor : VariantProcessor {
    override fun process(variant: BaseVariant) {
        val variantData = (variant as ApplicationVariantImpl).variantData
        val tasks = variantData.scope.globalScope.project.tasks
        val listPermission = tasks.findByName("listPermissions") ?: tasks.create("listPermissions")
        tasks.create(
            "list${variant.name.capitalize()}Permissions",
            ListPermissionTask::class.java
        ) {
            it.variant = variant
            // 如果闭包返回 false，则不能重用此任务的以前输出，并且将执行该任务
            // 这意味着任务已经过期，不会从构建缓存加载任何输出
            it.outputs.upToDateWhen { false }
        }.also {
            listPermission.dependsOn(it)
        }
    }

}