package com.dantrap.inspectapp.components.apps.list

import com.dantrap.inspectapp.components.apps.model.AppInfo
import kotlinx.coroutines.flow.StateFlow

interface AppListComponent {

    val appList: StateFlow<List<AppInfo>>

    fun onAppClick(packageName: String)
}
