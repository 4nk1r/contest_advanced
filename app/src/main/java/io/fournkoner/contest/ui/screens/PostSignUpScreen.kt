package io.fournkoner.contest.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.fournkoner.contest.R
import io.fournkoner.contest.ui.kit.ArrowsDown
import io.fournkoner.contest.ui.kit.BaseScreen
import io.fournkoner.contest.ui.kit.VSpace
import io.fournkoner.contest.ui.style.LocalAppColors
import io.fournkoner.contest.ui.style.Shapes
import io.fournkoner.contest.ui.style.Typography

@Composable
fun PostSignUpScreen() {
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
            TopTexts()
            StoreCards()
            ArrowsDown()
            VSpace(14.dp)
            CircleNumber(1)
            VSpace(14.dp)
            Text(
                text = stringResource(R.string.post_sign_up_guide_1),
                style = Typography.body1.copy(color = LocalAppColors.current.text),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            VSpace(14.dp)
            ArrowsDown()
            VSpace(14.dp)
            CircleNumber(2)
            VSpace(14.dp)
            Text(
                text = stringResource(R.string.post_sign_up_guide_2),
                style = Typography.body1.copy(color = LocalAppColors.current.text),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun TopTexts() {
    Text(
        text = stringResource(R.string.post_sign_up_you_signed_up),
        style = Typography.h6.copy(color = LocalAppColors.current.text),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(
            vertical = 16.dp,
            horizontal = 42.dp
        )
    )
    Text(
        text = buildAnnotatedString {
            withStyle(Typography.body2.copy(
                color = LocalAppColors.current.subtext,
                textDecoration = TextDecoration.Underline
            ).toSpanStyle()) {
                append(stringResource(R.string.post_sign_up_download_app))
            }
            withStyle(Typography.body2.copy(
                color = LocalAppColors.current.subtext,
            ).toSpanStyle()) {
                append(" ${stringResource(R.string.post_sign_up_and_get)} ")
            }
            withStyle(Typography.body2.copy(
                color = LocalAppColors.current.accent,
            ).toSpanStyle()) {
                append("150 KZT")
            }
            withStyle(Typography.body2.copy(
                color = LocalAppColors.current.subtext,
            ).toSpanStyle()) {
                append(" ${stringResource(R.string.post_sign_up_to_your_balance)}")
            }
        },
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 42.dp)
    )
}

@Composable
private fun StoreCards() {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (icon in 0..1) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArrowsDown()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = LocalAppColors.current.backgroundSecondary,
                            shape = Shapes.large
                        )
                        .border(
                            width = 1.dp,
                            color = LocalAppColors.current.accent,
                            shape = Shapes.large
                        )
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_download_appstore),
                        contentDescription = "Download on the App Store",
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(if (icon == 0) 1f else 0f) // to achieve same size of card
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_download_googleplay),
                        contentDescription = "Get it on Google Play",
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(if (icon == 1) 1f else 0f) // to achieve same size of card
                    )
                }
            }
        }
    }
}

@Composable
private fun CircleNumber(number: Int) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = LocalAppColors.current.accent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$number",
            style = Typography.h6.copy(color = LocalAppColors.current.background)
        )
    }
}