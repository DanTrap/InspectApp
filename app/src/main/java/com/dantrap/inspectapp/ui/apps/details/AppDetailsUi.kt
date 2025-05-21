package com.dantrap.inspectapp.ui.apps.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dantrap.inspectapp.R
import com.dantrap.inspectapp.components.apps.details.AppDetailsComponent
import com.dantrap.inspectapp.components.apps.details.FakeAppDetailsComponent
import com.dantrap.inspectapp.components.apps.model.AppInfo
import com.dantrap.inspectapp.core.theme.InspectAppTheme

@Composable
fun AppDetailsUi(
    component: AppDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val appInfo by component.appInfo.collectAsState()
    val checkSum by component.checkSum.collectAsState()

    Surface(modifier) {
        Column(
            modifier = Modifier.safeDrawingPadding()
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 4.dp, top = 8.dp),
                onClick = component::onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }

            AppInfoContent(
                modifier = Modifier.weight(1f),
                appInfo = appInfo,
                checkSum = checkSum
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = component::onOpenAppClick
            ) {
                Text(text = stringResource(R.string.apps_details_button_open_app))
            }
        }
    }
}

@Composable
private fun AppInfoContent(
    appInfo: AppInfo,
    checkSum: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(96.dp),
            bitmap = appInfo.icon,
            contentDescription = appInfo.appName
        )

        Text(
            text = appInfo.appName,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        InfoItem(
            label = stringResource(R.string.apps_details_version_name),
            value = appInfo.versionName
        )

        InfoItem(
            label = stringResource(R.string.apps_details_package_name),
            value = appInfo.packageName
        )

        checkSum?.let {
            InfoItem(
                label = stringResource(R.string.apps_details_sha256_checksum),
                value = it
            )
        } ?: LinearProgressIndicator()
    }
}

@Composable
private fun InfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = value, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
private fun AppDetailsPreview() {
    InspectAppTheme {
        AppDetailsUi(FakeAppDetailsComponent())
    }
}
