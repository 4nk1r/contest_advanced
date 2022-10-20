package io.fournkoner.contest.ui.kit

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import io.fournkoner.contest.ui.style.LocalAppColors
import io.fournkoner.contest.ui.style.Shapes
import io.fournkoner.contest.ui.style.Typography
import io.fournkoner.contest.utils.StringCallback
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppTextField(
    value: String,
    onValueChanged: StringCallback,
    placeholder: String,
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    shape: Shape = Shapes.large,
    bringIntoViewRequester: BringIntoViewRequester? = null,
) {
    val coroutineScope = rememberCoroutineScope()

    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        maxLines = 1,
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        cursorBrush = Brush.linearGradient(
            0f to LocalAppColors.current.accent,
            1f to LocalAppColors.current.accent
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .then(
                if (bringIntoViewRequester != null) {
                    Modifier.bringIntoViewRequester(bringIntoViewRequester)
                } else {
                    Modifier
                }
            )
            .onFocusEvent {
                if (it.isFocused) coroutineScope.launch { bringIntoViewRequester?.bringIntoView() }
            },
        textStyle = Typography.body1.copy(color = LocalAppColors.current.text)
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(
                    color = LocalAppColors.current.backgroundSecondary,
                    shape = shape
                )
                .border(
                    width = 1.dp,
                    color = LocalAppColors.current.accent,
                    shape = shape
                )
                .padding(
                    vertical = 16.dp,
                    horizontal = 24.dp
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = Typography.body1.copy(color = LocalAppColors.current.text)
                )
            }
            innerTextField()
        }
    }
}