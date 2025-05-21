package com.dantrap.inspectapp.components.apps.list

import com.dantrap.inspectapp.components.apps.model.AppInfo
import kotlinx.coroutines.flow.MutableStateFlow

class FakeListComponent : AppListComponent {

    override val appList = MutableStateFlow(AppInfo.MOCKS)

    override fun onAppClick(packageName: String) = Unit
}
