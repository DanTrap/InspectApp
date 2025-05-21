package com.dantrap.inspectapp.components.apps.repository

import com.dantrap.inspectapp.components.apps.model.AppInfo

interface AppInfoRepository {

    fun getAppInfo(packageName: String): AppInfo

    fun getInstalledAppsInfo(): List<AppInfo>

    suspend fun calculateApkSha256Checksum(packageName: String): String?
}
