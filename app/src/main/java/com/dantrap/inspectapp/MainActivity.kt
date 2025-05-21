package com.dantrap.inspectapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.dantrap.inspectapp.components.root.RealRootComponent
import com.dantrap.inspectapp.core.theme.InspectAppTheme
import com.dantrap.inspectapp.ui.root.RootUi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val rootComponent = RealRootComponent(
            componentContext = defaultComponentContext(),
            appContainer = (application as App).appContainer
        )

        setContent {
            InspectAppTheme {
                RootUi(rootComponent)
            }
        }
    }
}
