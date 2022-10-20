package io.fournkoner.contest.ui.kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fournkoner.contest.R

//переиспользуется на 3 экранах
@Composable
fun AppLogo() {
    Image(
        painter = painterResource(R.drawable.ic_app_logo),
        contentDescription = stringResource(R.string.app_name),
        modifier = Modifier
            .padding(horizontal = 90.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}