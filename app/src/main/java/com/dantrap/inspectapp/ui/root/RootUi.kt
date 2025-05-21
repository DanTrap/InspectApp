package com.dantrap.inspectapp.ui.root

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.materialPredictiveBackAnimatable
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.dantrap.inspectapp.components.root.FakeRootComponent
import com.dantrap.inspectapp.components.root.RootComponent
import com.dantrap.inspectapp.core.theme.InspectAppTheme
import com.dantrap.inspectapp.ui.apps.details.AppDetailsUi
import com.dantrap.inspectapp.ui.apps.list.AppListUi

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val stack by component.childStack.subscribeAsState()

    Children(
        modifier = modifier,
        stack = stack,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            onBack = component::onBackClick,
            fallbackAnimation = stackAnimation(slide()),
            selector = { backEvent, _, _ ->
                materialPredictiveBackAnimatable(backEvent)
            }
        )
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.List -> AppListUi(child.component)
            is RootComponent.Child.Details -> AppDetailsUi(child.component)
        }
    }

    StatusBarColor()
}

@Composable
private fun StatusBarColor() {
    val scrim = MaterialTheme.colorScheme.surface.copy(
        alpha = if (isSystemInDarkTheme()) 0.5f else 0.8f
    )

    Box(Modifier.fillMaxSize()) {
        Spacer(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .drawBehind {
                    drawRect(scrim)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RootPreview() {
    InspectAppTheme {
        RootUi(FakeRootComponent())
    }
}
