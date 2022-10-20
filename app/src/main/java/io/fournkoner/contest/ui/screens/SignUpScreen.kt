package io.fournkoner.contest.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fournkoner.contest.R
import io.fournkoner.contest.ui.kit.*
import io.fournkoner.contest.ui.navigation.Screen
import io.fournkoner.contest.ui.style.LocalAppColors
import io.fournkoner.contest.ui.style.Shapes
import io.fournkoner.contest.ui.style.Typography
import io.fournkoner.contest.utils.Function

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var login by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var isTermsOfUseChecked by rememberSaveable { mutableStateOf(false) }

    val firstNameFocusRequester = remember { FocusRequester() }
    val lastNameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val phoneFocusRequester = remember { FocusRequester() }

    val loginBringIntoViewRequester = remember { BringIntoViewRequester() }
    val firstNameBringIntoViewRequester = remember { BringIntoViewRequester() }
    val lastNameBringIntoViewRequester = remember { BringIntoViewRequester() }
    val emailBringIntoViewRequester = remember { BringIntoViewRequester() }
    val passwordBringIntoViewRequester = remember { BringIntoViewRequester() }
    val phoneBringIntoViewRequester = remember { BringIntoViewRequester() }

    fun signUp() {
        navController.navigate(Screen.POST_SIGN_UP)
    }

    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppLogo()
            VSpace(18.dp)
            PartiallyHighlightedText(
                mainText = stringResource(R.string.sign_up_invited_by),
                highlightedPart = "id33333 Alexey Alexey"
            )
            VSpace(4.dp)
            PartiallyHighlightedText(
                mainText = stringResource(R.string.sign_up_referral_award),
                highlightedPart = "999 KZT"
            )
            VSpace(12.dp)
            Text(
                text = stringResource(R.string.sign_up_finish_signing_up),
                style = Typography.body2.copy(color = LocalAppColors.current.subtext)
            )
            VSpace(12.dp)
            ArrowsDown()
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppTextField(
                    value = login,
                    onValueChanged = { login = it },
                    placeholder = stringResource(R.string.sign_up_login_placeholder),
                    shape = Shapes.large,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { firstNameFocusRequester.requestFocus() }
                    ),
                    bringIntoViewRequester = loginBringIntoViewRequester
                )
                AppTextField(
                    value = firstName,
                    onValueChanged = { firstName = it },
                    placeholder = stringResource(R.string.sign_up_first_name_placeholder),
                    shape = Shapes.large,
                    focusRequester = firstNameFocusRequester,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { lastNameFocusRequester.requestFocus() }
                    ),
                    bringIntoViewRequester = firstNameBringIntoViewRequester
                )
                AppTextField(
                    value = lastName,
                    onValueChanged = { lastName = it },
                    placeholder = stringResource(R.string.sign_up_last_name_placeholder),
                    shape = Shapes.large,
                    focusRequester = lastNameFocusRequester,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { emailFocusRequester.requestFocus() }
                    ),
                    bringIntoViewRequester = lastNameBringIntoViewRequester
                )
                AppTextField(
                    value = email,
                    onValueChanged = { email = it },
                    placeholder = stringResource(R.string.sign_up_email_placeholder),
                    shape = Shapes.large,
                    focusRequester = emailFocusRequester,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { passwordFocusRequester.requestFocus() }
                    ),
                    bringIntoViewRequester = emailBringIntoViewRequester
                )
                AppTextField(
                    value = password,
                    onValueChanged = { password = it },
                    placeholder = stringResource(R.string.sign_up_password_placeholder),
                    shape = Shapes.large,
                    focusRequester = passwordFocusRequester,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { phoneFocusRequester.requestFocus() }
                    ),
                    bringIntoViewRequester = passwordBringIntoViewRequester
                )
                AppTextField(
                    value = phone,
                    onValueChanged = { phone = it },
                    placeholder = stringResource(R.string.sign_up_phone_placeholder),
                    shape = Shapes.large,
                    focusRequester = phoneFocusRequester,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { signUp() }
                    ),
                    bringIntoViewRequester = phoneBringIntoViewRequester
                )
                VSpace(size = 0.dp)
                TermsOfUseCheckbox(isTermsOfUseChecked) {
                    isTermsOfUseChecked = !isTermsOfUseChecked
                }
                VSpace(size = 0.dp)
            }
            AppButton(
                text = stringResource(R.string.sign_up_sign_up),
                onClick = ::signUp
            )
        }
    }
}

@Composable
private fun PartiallyHighlightedText(
    mainText: String,
    highlightedPart: String,
) {
    Text(
        text = buildAnnotatedString {
            withStyle(Typography.body2.copy(color = LocalAppColors.current.subtext).toSpanStyle()) {
                append("$mainText ")
            }
            withStyle(Typography.body2.copy(color = LocalAppColors.current.accent).toSpanStyle()) {
                append(highlightedPart)
            }
        }
    )
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
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
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
            text = stringResource(R.string.sign_up_agree_terms_of_use),
            style = Typography.body2.copy(
                color = LocalAppColors.current.text,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}