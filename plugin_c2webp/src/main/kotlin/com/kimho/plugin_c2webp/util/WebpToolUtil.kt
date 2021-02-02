package com.kimho.plugin_c2webp.util

import com.kimho.plugin_c2webp.bean.WebpToolBean

/**
 * @Description:
 * @Author: chenjinhong
 * @Date: 2021/2/2
 */
class WebpToolUtil {

    companion object {

        fun cmd(cmd: String, params: String) {
            val system = System.getProperty("os.name")
            val cmdStr = when (system) {
                "Windows" ->
                    "${WebpToolBean.getToolsDirPath()}/windows/$cmd $params"
                "Mac OS X" ->
                    "${WebpToolBean.getToolsDirPath()}/mac/$cmd $params"
                else -> ""
            }
            if (cmd == "") {
                println("Cwebp can't support this system.")
                return
            }
            outputMessage(cmdStr)
        }

        private fun outputMessage(cmdStr: String) {
            val process = Runtime.getRuntime().exec(cmdStr)
            process.waitFor()
        }

    }
}