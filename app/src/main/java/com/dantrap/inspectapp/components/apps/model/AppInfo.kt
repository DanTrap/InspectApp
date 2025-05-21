package com.dantrap.inspectapp.components.apps.model

import androidx.compose.ui.graphics.ImageBitmap

data class AppInfo(
    val packageName: String,
    val appName: String,
    val icon: ImageBitmap,
    val versionName: String,
) {
    companion object {
        val MOCK = AppInfo(
            packageName = "com.example.app",
            appName = "Example App",
            icon = ImageBitmap(1, 1),
            versionName = "1.0.0"
        )

        val MOCKS = buildList { repeat(10) { add(MOCK.copy(packageName = "com.example.app$it")) } }
    }
}
