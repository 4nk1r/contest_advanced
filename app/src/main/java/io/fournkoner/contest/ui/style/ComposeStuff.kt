package io.fournkoner.contest.ui.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.fournkoner.contest.R

@Composable
fun ContestTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppColors provides AppColors()) {
        MaterialTheme(
            colors = darkColors(background = LocalAppColors.current.background),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

val Typography = Typography(
    h6 = TextStyle(
        fontFamily = FontFamily(Font(R.font.content_title_font)),
        fontSize = 18.sp,
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.content_body_font)),
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.content_body_font)),
        fontSize = 14.sp,
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.button_font)),
        fontSize = 18.sp,
        color = Color.White
    )
)

val Shapes = Shapes(
    small = RoundedCornerShape(0.dp),
    medium = RoundedCornerShape(5.dp),
    large = RoundedCornerShape(8.dp)
)
