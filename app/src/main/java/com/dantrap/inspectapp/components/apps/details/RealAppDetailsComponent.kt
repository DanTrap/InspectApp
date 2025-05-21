package com.dantrap.inspectapp.components.apps.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.dantrap.inspectapp.components.apps.model.AppInfo
import com.dantrap.inspectapp.components.apps.repository.AppInfoRepository
import com.dantrap.inspectapp.core.external_apps.ExternalAppsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class RealAppDetailsComponent(
    componentContext: ComponentContext,
    appInfoRepository: AppInfoRepository,
    private val onNavigateBack: () -> Unit,
    private val packageName: String,
    private val externalAppsService: ExternalAppsService,
) : ComponentContext by componentContext, AppDetailsComponent {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    init {
        doOnDestroy(scope::cancel)
    }

    override val appInfo: StateFlow<AppInfo> = MutableStateFlow(
        appInfoRepository.getAppInfo(packageName)
    )

    override val checkSum: StateFlow<String?> = flow<String?> {
        appInfoRepository.calculateApkSha256Checksum(packageName).also { emit(it) }
    }.stateIn(scope, SharingStarted.WhileSubscribed(), null)

    override fun onOpenAppClick() = externalAppsService.openApp(packageName)

    override fun onBackClick() = onNavigateBack()
}
