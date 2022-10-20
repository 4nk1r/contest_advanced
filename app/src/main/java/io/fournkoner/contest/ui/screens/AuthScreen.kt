package io.fournkoner.contest.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.fournkoner.contest.R
import io.fournkoner.contest.ui.kit.*
import io.fournkoner.contest.ui.navigation.ARG_SELECTED_BRANCH
import io.fournkoner.contest.ui.navigation.BottomSheet
import io.fournkoner.contest.ui.navigation.Screen
import io.fournkoner.contest.ui.style.LocalAppColors
import io.fournkoner.contest.ui.style.Shapes
import io.fournkoner.contest.ui.style.Typography
import io.fournkoner.contest.utils.Function
import io.fournkoner.contest.utils.StringCallback

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthScreen(navController: NavHostController) {
    val selectedBranch = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow(ARG_SELECTED_BRANCH, "")?.collectAsState()
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isTermsOfUseChecked by rememberSaveable { mutableStateOf(false) }

    val passwordFocusRequester = remember { FocusRequester() }

    fun signIn() {
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
                text = stringResource(R.string.auth_fill_fields),
                style = Typography.h6.copy(color = LocalAppColors.current.text),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            BranchSelection(value = selectedBranch?.value) {
                navController.navigate(BottomSheet.CHOOSE_BRANCH)
            }
            VSpace(size = 18.dp)
            AppTextField(
                value = login,
                onValueChanged = { login = it },
                placeholder = stringResource(R.string.auth_login_placeholder),
                shape = Shapes.medium,
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            VSpace(size = 18.dp)
            AppTextField(
                value = password,
                onValueChanged = { password = it },
                placeholder = stringResource(R.string.auth_password_placeholder),
                shape = Shapes.medium,
                focusRequester = passwordFocusRequester,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { signIn() }
                )
            )
            VSpace(size = 18.dp)
            TermsOfUseCheckbox(isTermsOfUseChecked) { isTermsOfUseChecked = !isTermsOfUseChecked }
            VSpace(size = 18.dp)
            AppButton(
                text = stringResource(R.string.auth_sign_in),
                onClick = ::signIn,
            )
            VSpace(size = 22.dp)
            FooterText(
                text = stringResource(R.string.auth_no_account),
                action = stringResource(R.string.auth_sign_up)
            ) { navController.navigate(Screen.SIGN_UP) }
            VSpace(size = 22.dp)
            FooterText(
                text = stringResource(R.string.auth_forgot_password),
                action = stringResource(R.string.auth_restore)
            ) { navController.navigate(Screen.RESTORE) }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BranchSelection(
    value: String?,
    onClick: Function,
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(
                color = LocalAppColors.current.backgroundSecondary,
                shape = Shapes.medium
            )
            .border(
                width = 1.dp,
                color = LocalAppColors.current.accent,
                shape = Shapes.medium
            )
            .clip(Shapes.medium)
            .clickable(onClick = onClick)
            .padding(
                vertical = 16.dp,
                horizontal = 24.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnimatedContent(
            targetState = value?.takeIf { it.isNotEmpty() },
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = it ?: stringResource(R.string.auth_select_branch),
                style = Typography.body1.copy(color = LocalAppColors.current.text),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            painter = painterResource(R.drawable.ic_arrow_down),
            contentDescription = stringResource(R.string.auth_select_branch),
            modifier = Modifier.size(16.dp),
            tint = LocalAppColors.current.text
        )
    }
}

@Composable
private fun TermsOfUseCheckbox(
    value: Boolean,
    onChangeValue: Function,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onChangeValue
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Checkbox(
            checked = value,
            onCheckedChange = { onChangeValue() },
            colors = CheckboxDefaults.colors(
                checkedColor = LocalAppColors.current.accent,
                uncheckedColor = LocalAppColors.current.text,
                checkmarkColor = LocalAppColors.current.text,
            ),
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringResource(R.string.auth_agree_temrs_of_use),
            style = Typography.body2.copy(
                color = LocalAppColors.current.text,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}

@Composable
private fun FooterText(
    text: String,
    action: String,
    onActionClick: Function,
) {
    val annotatedString = buildAnnotatedString {
        withStyle(Typography.body2.copy(color = LocalAppColors.current.subtext).toSpanStyle()) {
            append("$text ")
        }

        pushStringAnnotation(
            tag = "action",
            annotation = "action"
        )
        withStyle(Typography.body2.copy(
            color = LocalAppColors.current.subtext,
            textDecoration = TextDecoration.Underline
        ).toSpanStyle()) {
            append(action)
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = "action",
                start = offset,
                end = offset
            ).firstOrNull()?.let { onActionClick() }
        }
    )
}

@Composable
fun ChooseBranchBottomSheet(
    onSelectBranch: StringCallback
) {
    val branches = remember {
        listOf(
            "Never gonna give you up",
            "Never gonna let you down",
            "Never gonna run around and desert you",
            "Never gonna make you cry",
            "Never gonna say goodbye",
            "Never gonna tell a lie and hurt you"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LocalAppColors.current.background)
            .padding(vertical = 16.dp)
            .navigationBarsPadding(),
    ) {
        branches.forEach { branch ->
            Text(
                text = branch,
                style = Typography.body1.copy(color = LocalAppColors.current.text),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectBranch(branch) }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    }
}