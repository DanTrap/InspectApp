package com.dantrap.inspectapp.components.root

import androidx.activity.OnBackPressedDispatcher
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandler
import com.dantrap.inspectapp.components.apps.list.FakeListComponent

class FakeRootComponent : RootComponent {

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = MutableValue(
        ChildStack(
            configuration = "<fake>",
            instance = RootComponent.Child.List(FakeListComponent())
        )
    )

    override val backHandler: BackHandler = BackHandler(OnBackPressedDispatcher())

    override fun onBackClick() = Unit
}
