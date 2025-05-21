package com.dantrap.inspectapp.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.dantrap.inspectapp.components.apps.details.AppDetailsComponent
import com.dantrap.inspectapp.components.apps.list.AppListComponent

interface RootComponent : BackHandlerOwner {

    val childStack: Value<ChildStack<*, Child>>

    fun onBackClick()

    sealed interface Child {
        data class List(val component: AppListComponent) : Child
        data class Details(val component: AppDetailsComponent) : Child
    }
}
