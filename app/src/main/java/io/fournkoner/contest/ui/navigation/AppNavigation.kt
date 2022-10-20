package io.fournkoner.contest.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import io.fournkoner.contest.ui.screens.*

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavigation() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        modifier = Modifier.fillMaxSize(),
        sheetShape = RectangleShape,
        scrimColor = Color.Black.copy(alpha = 0.4f)
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.AUTH
        ) {
            composable(Screen.AUTH) {
                AuthScreen(navController)
            }
            composable(Screen.SIGN_UP) {
                SignUpScreen(navController)
            }
            composable(Screen.RESTORE) {
                RestoreScreen(navController)
            }
            composable(Screen.POST_SIGN_UP) {
                PostSignUpScreen()
            }

            bottomSheet(BottomSheet.CHOOSE_BRANCH) {
                ChooseBranchBottomSheet {
                    navController.apply {
                        previousBackStackEntry?.savedStateHandle?.set(ARG_SELECTED_BRANCH, it)
                        popBackStack()
                    }
                }
            }
        }
    }
}

object Screen {

    const val AUTH = "auth"
    const val SIGN_UP = "sign_up"
    const val RESTORE = "restore"
    const val POST_SIGN_UP = "post_sign_up"
}

object BottomSheet {

    const val CHOOSE_BRANCH = "choose_branch"
}

const val ARG_SELECTED_BRANCH = "selected_branch"