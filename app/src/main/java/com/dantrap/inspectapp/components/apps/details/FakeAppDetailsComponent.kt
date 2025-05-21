package com.dantrap.inspectapp.components.apps.details

import com.dantrap.inspectapp.components.apps.model.AppInfo
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAppDetailsComponent : AppDetailsComponent {

    override val appInfo = MutableStateFlow(AppInfo.MOCK)
    override val checkSum = MutableStateFlow(null)

    override fun onOpenAppClick() = Unit
    override fun onBackClick() = Unit
}
