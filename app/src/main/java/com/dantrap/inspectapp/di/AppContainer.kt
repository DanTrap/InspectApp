package com.dantrap.inspectapp.di

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.dantrap.inspectapp.components.apps.details.AppDetailsComponent
import com.dantrap.inspectapp.components.apps.details.RealAppDetailsComponent
import com.dantrap.inspectapp.components.apps.list.AppListComponent
import com.dantrap.inspectapp.components.apps.list.RealAppListComponent
import com.dantrap.inspectapp.components.apps.repository.AppInfoRepository
import com.dantrap.inspectapp.components.apps.repository.AppInfoRepositoryImpl
import com.dantrap.inspectapp.core.external_apps.ExternalAppsService
import com.dantrap.inspectapp.core.external_apps.ExternalAppsServiceImpl

class AppContainer(context: Context) {

    val appInfoRepository: AppInfoRepository by lazy {
        AppInfoRepositoryImpl(context)
    }

    val externalAppsService: ExternalAppsService by lazy {
        ExternalAppsServiceImpl(context)
    }

    fun createAppListComponent(
        componentContext: ComponentContext,
        onAppSelect: (packageName: String) -> Unit,
    ): AppListComponent = RealAppListComponent(
        componentContext = componentContext,
        onAppSelect = onAppSelect,
        appInfoRepository = appInfoRepository
    )

    fun createAppDetailsComponent(
        componentContext: ComponentContext,
        packageName: String,
        onNavigateBack: () -> Unit,
    ): AppDetailsComponent = RealAppDetailsComponent(
        componentContext = componentContext,
        packageName = packageName,
        onNavigateBack = onNavigateBack,
        appInfoRepository = appInfoRepository,
        externalAppsService = externalAppsService
    )
}
