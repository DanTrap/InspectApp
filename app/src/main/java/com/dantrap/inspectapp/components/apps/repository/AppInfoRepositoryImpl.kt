package com.dantrap.inspectapp.components.apps.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import com.dantrap.inspectapp.components.apps.model.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.security.DigestInputStream
import java.security.MessageDigest

internal class AppInfoRepositoryImpl(context: Context) : AppInfoRepository {

    private val packageManager = context.packageManager

    override suspend fun calculateApkSha256Checksum(
        packageName: String,
    ): String? = withContext(Dispatchers.IO) {
        runCatching {
            val apkFile = packageManager
                .getPackageInfo(packageName, 0)
                .applicationInfo
                ?.sourceDir
                ?.let(::File) ?: return@withContext null

            val digest = MessageDigest.getInstance("SHA-256")

            DigestInputStream(apkFile.inputStream().buffered(), digest).use { stream ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                while (stream.read(buffer) != -1) {
                }
            }

            digest.digest().joinToString("") { "%02x".format(it) }
        }.getOrNull()
    }

    override fun getAppInfo(packageName: String): AppInfo = packageManager.run {
        AppInfo(
            packageName = packageName,
            appName = getApplicationLabel(getApplicationInfo(packageName, 0)).toString(),
            icon = getApplicationIcon(packageName).toBitmap().asImageBitmap(),
            versionName = getPackageInfo(packageName, 0).versionName ?: "N/A"
        )
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun getInstalledAppsInfo(): List<AppInfo> = packageManager
        .getInstalledApplications(PackageManager.GET_META_DATA)
        .filter { appInfo ->
            packageManager.getLaunchIntentForPackage(appInfo.packageName) != null
        }
        .map { appInfo ->
            getAppInfo(appInfo.packageName)
        }
        .sortedBy(AppInfo::appName)
}
