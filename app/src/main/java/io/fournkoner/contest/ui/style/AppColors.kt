package io.fournkoner.contest.ui.style

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val background: Color = Color(0xFF0E1821),
    val backgroundSecondary: Color = Color(0xFF151D26),
    val accent: Color = Color(0xFFB42A40),
    val text: Color = Color(0xFFFFFFFF),
    val subtext: Color = Color(0xFF697786)
)

val LocalAppColors = compositionLocalOf<AppColors> { error("LocalAppColors provides nothing") }