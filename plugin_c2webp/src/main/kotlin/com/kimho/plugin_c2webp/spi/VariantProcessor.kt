package com.kimho.plugin_c2webp.spi

import com.android.build.gradle.api.BaseVariant

/**
 * @Description:Task 注册接口
 * @Author: chenjinhong
 * @Date: 2021/2/2
 */

interface VariantProcessor {

    fun process(variant: BaseVariant)

}