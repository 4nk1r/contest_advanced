package io.fournkoner.contest.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fournkoner.contest.R
import io.fournkoner.contest.ui.kit.*
import io.fournkoner.contest.ui.navigation.ARG_SELECTED_BRANCH
import io.fournkoner.contest.ui.navigation.BottomSheet
import io.fournkoner.contest.ui.navigation.Screen
import io.fournkoner.contest.ui.style.LocalAppColors
import io.fournkoner.contest.ui.style.Shapes
import io.fournkoner.contest.ui.style.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RestoreScreen(navController: NavController) {
    val selectedBranch = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow(ARG_SELECTED_BRANCH, "")?.collectAsState()
    var login by rememberSaveable { mutableStateOf("") }

    fun restore() {
        navController.navigate(Screen.POST_SIGN_UP)
    }

    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .systemBarsPadding()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppLogo()
            Text(
                text = stringResource(R.string.restore_title),
                style = Typography.h6.copy(color = LocalAppColors.current.text),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            BranchSelection(value = selectedBranch?.value) {
                navController.navigate(BottomSheet.CHOOSE_BRANCH)
            }
            VSpace(size = 28.dp)
            AppTextField(
                value = login,
                onValueChanged = { login = it },
                placeholder = stringResource(R.string.auth_login_placeholder),
                shape = Shapes.medium,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { restore() }
                )
            )
            VSpace(size = 28.dp)
            AppButton(
                text = stringResource(R.string.auth_sign_in),
                onClick = ::restore,
            )
            VSpace(size = 22.dp)
            Text(
                text = stringResource(R.string.restore_already_have_account),
                style = Typography.body2.copy(
                    color = LocalAppColors.current.subtext,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = navController::popBackStack
                    )
            )
        }
    }
}