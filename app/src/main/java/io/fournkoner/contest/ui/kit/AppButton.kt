package io.fournkoner.contest.ui.kit

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.fournkoner.contest.R
import io.fournkoner.contest.ui.style.LocalAppColors
import io.fournkoner.contest.ui.style.Typography
import io.fournkoner.contest.utils.Function

@Composable
fun AppButton(
    text: String,
    onClick: Function,
    isEnabled: Boolean = true,
) {
    val enableTransition = updateTransition(targetState = isEnabled, label = "button_enable")

    Box(
        modifier = Modifier
            .alpha(enableTransition.animateFloat(
                label = "alpha",
                transitionSpec = { tween() }
            ) { if (it) 1f else 0.6f }.value)
            .scale(enableTransition.animateFloat(
                label = "scale",
                transitionSpec = { tween() }
            ) { if (it) 1f else 0.9f }.value)
            .fillMaxWidth()
            .height(56.dp)
            .clipToBounds()
            .clickable(enabled = isEnabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.app_button_texture),
            contentDescription = text,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            style = Typography.button.copy(color = LocalAppColors.current.text),
            modifier = Modifier.padding(horizontal = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}