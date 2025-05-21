package com.dantrap.inspectapp.components.apps.list

import com.arkivanov.decompose.ComponentContext
import com.dantrap.inspectapp.components.apps.model.AppInfo
import com.dantrap.inspectapp.components.apps.repository.AppInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class RealAppListComponent(
    componentContext: ComponentContext,
    private val onAppSelect: (String) -> Unit,
    appInfoRepository: AppInfoRepository,
) : ComponentContext by componentContext, AppListComponent {

    override val appList: StateFlow<List<AppInfo>> = MutableStateFlow(
        appInfoRepository.getInstalledAppsInfo()
    )

    override fun onAppClick(packageName: String) = onAppSelect(packageName)
}
