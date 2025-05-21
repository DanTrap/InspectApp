package com.dantrap.inspectapp.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.dantrap.inspectapp.di.AppContainer
import kotlinx.serialization.Serializable

internal class RealRootComponent(
    componentContext: ComponentContext,
    private val appContainer: AppContainer,
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild,
        serializer = ChildConfig.serializer()
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext,
    ) = when (config) {
        ChildConfig.List -> RootComponent.Child.List(
            appContainer.createAppListComponent(
                componentContext = componentContext,
                onAppSelect = { navigation.pushNew(ChildConfig.Details(it)) }
            )
        )

        is ChildConfig.Details -> RootComponent.Child.Details(
            appContainer.createAppDetailsComponent(
                componentContext = componentContext,
                packageName = config.packageName,
                onNavigateBack = navigation::pop
            )
        )
    }

    override fun onBackClick() = navigation.pop()

    @Serializable
    private sealed interface ChildConfig {

        @Serializable
        data object List : ChildConfig

        @Serializable
        data class Details(val packageName: String) : ChildConfig
    }
}
