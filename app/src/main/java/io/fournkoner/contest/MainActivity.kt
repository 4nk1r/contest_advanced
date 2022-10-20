package io.fournkoner.contest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.fournkoner.contest.ui.navigation.AppNavigation
import io.fournkoner.contest.ui.style.ContestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extendScreen()
        setContent {
            ContestTheme {
                AppNavigation()

                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                }
            }
        }
    }

    private fun extendScreen() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}