package com.dantrap.inspectapp.components.apps.details

import com.dantrap.inspectapp.components.apps.model.AppInfo
import kotlinx.coroutines.flow.StateFlow

interface AppDetailsComponent {

    val appInfo: StateFlow<AppInfo>
    val checkSum: StateFlow<String?>

    fun onOpenAppClick()
    fun onBackClick()
}
