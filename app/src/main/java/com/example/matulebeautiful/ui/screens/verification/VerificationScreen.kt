package com.example.matulebeautiful.ui.screens.verification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matulebeautiful.R
import com.example.matulebeautiful.common.formatTime
import com.example.matulebeautiful.common.generatePassword
import com.example.matulebeautiful.domain.navigation.NavDestinations
import com.example.matulebeautiful.ui.components.CustomTextField
import com.example.matulebeautiful.ui.components.OnBackgroundBackButton
import com.example.matulebeautiful.ui.components.PrimaryMaxWidthButton
import com.example.matulebeautiful.ui.theme.poppins
import com.example.matulebeautiful.ui.theme.raleway
import kotlinx.coroutines.delay

@Composable
fun VerificationScreen(navController: NavController, viewModel: VerificationViewModel) {
    LaunchedEffect(key1 = viewModel.state.time) {
        if (viewModel.state.time > 0) {
            delay(1000)
            viewModel.state = viewModel.state.copy(time = viewModel.state.time - 1)
        }
    }

    val modifier = if (viewModel.state.isModalOpen) Modifier
        .background(Color(0x40000000))
        .blur(4.dp) else Modifier
    val focusRequesters = remember {
        listOf(
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester()
        )
    }
    val clipboardManager = LocalClipboardManager.current

    Box(modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(23.dp))
            OnBackgroundBackButton(navController)
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = "OTP проверка",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                fontFamily = raleway,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения",
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "OTP Код",
                fontFamily = raleway,
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(6) { i ->
                    var fieldModifier =
                        if (i != 0) modifier.focusRequester(focusRequesters[i - 1]) else Modifier
                    fieldModifier =
                        if (viewModel.state.values[i].isEmpty() || viewModel.state.isError) fieldModifier.border(
                            1.dp,
                            colorScheme.error,
                            RoundedCornerShape(12.dp)
                        ) else fieldModifier
                    TextField(
                        value = viewModel.state.values[i],
                        onValueChange = {
                            if (it.length <= 1) {
                                val values = viewModel.state.values.toMutableList()
                                values[i] = it
                                viewModel.state =
                                    viewModel.state.copy(values = values, isError = false)
                            }
                            if (it.length == 1 && i != 5) {
                                focusRequesters[i].requestFocus()
                            }
                            if (it.length == 1 && i == 5) {
                                viewModel.checkOtp()
                            }
                        },
                        modifier = fieldModifier
                            .weight(1f)
                            .height(100.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        textStyle = TextStyle(
                            fontFamily = poppins,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = colorScheme.surface,
                            focusedContainerColor = colorScheme.surface,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Отправить заново",
                    color = colorScheme.onSurface,
                    fontFamily = raleway,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        viewModel.resendOtp()
                    }
                )
                Text(
                    text = viewModel.state.time.formatTime(),
                    color = colorScheme.onSurface,
                    fontFamily = raleway,
                    fontSize = 12.sp
                )
            }
        }
    }
    if (viewModel.state.isModalOpen) {
        Box(
            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(colorScheme.background, RoundedCornerShape(16.dp))
                    .padding(horizontal = 10.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.state.passwordGenerationState == PasswordGenerationState.Phrase) {
                    Text(
                        text = "Введите кодовую фразу",
                        fontFamily = raleway,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CustomTextField(
                        placeholder = "Фраза",
                        value = viewModel.state.phrase,
                        onValueChanged = { viewModel.state = viewModel.state.copy(phrase = it) }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    PrimaryMaxWidthButton(text = "Сгенерировать пароль") {
                        viewModel.generatePassword()
                    }
                } else {
                    Text(
                        text = "Ваш новый пароль:",
                        fontFamily = raleway,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SelectionContainer {
                        Text(
                            text = viewModel.state.password,
                            fontFamily = raleway,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    PrimaryMaxWidthButton(text = "Копировать") {
                        clipboardManager.setText(AnnotatedString(viewModel.state.password))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    PrimaryMaxWidthButton(text = "Сохранить") {
                        navController.navigate(NavDestinations.Home)
                    }
                }
            }
        }
    }
}