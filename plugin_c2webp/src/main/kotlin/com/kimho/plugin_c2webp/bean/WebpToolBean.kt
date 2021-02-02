package com.kimho.plugin_c2webp.bean

import java.io.File

/**
 * @Description: cwebp 工具路径
 * @Author: chenjinhong
 * @Date: 2021/2/2
 */


object WebpToolBean {
    private lateinit var rootDir: String

    fun setRootDir(rootDir: String) {
        WebpToolBean.rootDir = rootDir
    }

    fun getRootDirPath(): String {
        return rootDir
    }

    fun getToolsDir(): File {
        return File("$rootDir/tools/cwebp")
    }

    fun getToolsDirPath(): String {
        return "$rootDir/tools/cwebp"
    }
}