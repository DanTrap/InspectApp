package com.dantrap.inspectapp.ui.apps.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dantrap.inspectapp.components.apps.list.AppListComponent
import com.dantrap.inspectapp.components.apps.list.FakeListComponent
import com.dantrap.inspectapp.components.apps.model.AppInfo
import com.dantrap.inspectapp.core.theme.InspectAppTheme

@Composable
fun AppListUi(
    component: AppListComponent,
    modifier: Modifier = Modifier,
) {
    val appList by component.appList.collectAsState()

    Surface(modifier) {
        LazyColumn(
            contentPadding = WindowInsets.safeDrawing.asPaddingValues()
        ) {
            items(
                items = appList,
                key = { it.packageName }
            ) {
                AppInfoItem(
                    appInfo = it,
                    onClick = component::onAppClick,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun AppInfoItem(
    appInfo: AppInfo,
    onClick: (packageName: String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(appInfo.packageName) }
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier.size(56.dp),
            bitmap = appInfo.icon,
            contentDescription = appInfo.appName
        )
        Text(text = appInfo.appName)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AppListPreview() {
    InspectAppTheme {
        AppListUi(FakeListComponent())
    }
}
