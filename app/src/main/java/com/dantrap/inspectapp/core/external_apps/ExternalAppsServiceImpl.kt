package com.dantrap.inspectapp.core.external_apps

import android.content.Context

internal class ExternalAppsServiceImpl(
    private val context: Context,
) : ExternalAppsService {

    override fun openApp(packageName: String) {
        context.packageManager
            .getLaunchIntentForPackage(packageName)
            ?.run(context::startActivity)
    }
}
