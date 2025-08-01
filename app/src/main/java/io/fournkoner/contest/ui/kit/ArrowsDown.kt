package io.fournkoner.contest.ui.kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.fournkoner.contest.R

//переиспользуется на двух экранах
@Composable
fun ArrowsDown() {
    Image(
        painter = painterResource(R.drawable.ic_three_arrows_down),
        contentDescription = null,
        modifier = Modifier.size(32.dp)
    )
}