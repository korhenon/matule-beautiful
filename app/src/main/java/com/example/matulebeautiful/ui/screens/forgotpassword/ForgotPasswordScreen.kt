package com.example.matulebeautiful.ui.screens.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matulebeautiful.R
import com.example.matulebeautiful.common.isValidEmail
import com.example.matulebeautiful.domain.navigation.NavDestinations
import com.example.matulebeautiful.ui.components.CustomTextField
import com.example.matulebeautiful.ui.components.OnBackgroundBackButton
import com.example.matulebeautiful.ui.components.PrimaryMaxWidthButton
import com.example.matulebeautiful.ui.screens.verification.VerificationViewModel
import com.example.matulebeautiful.ui.theme.poppins
import com.example.matulebeautiful.ui.theme.raleway

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    verificationViewModel: VerificationViewModel,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val modifier = if (viewModel.state.isModalOpen) Modifier
        .background(Color(0x40000000))
        .blur(4.dp) else Modifier
    val modalInteractionSource = remember { MutableInteractionSource() }

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
                text = "Забыл пароль",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                fontFamily = raleway,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Введите свою учетную запись для сброса",
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextField(
                placeholder = "xyz@gmail.com",
                value = viewModel.state.email,
                onValueChanged = { viewModel.state = viewModel.state.copy(email = it) },
                isError = !viewModel.state.email.isValidEmail() && viewModel.state.email != ""
            )
            Spacer(modifier = Modifier.height(40.dp))
            PrimaryMaxWidthButton(text = "Отправить") {
                viewModel.sendOtp()
            }
        }
    }
    if (viewModel.state.isModalOpen) {
        Box(
            Modifier
                .fillMaxSize()
                .clickable {
                    verificationViewModel.init(viewModel.state.email)
                    navController.navigate(NavDestinations.Verification)
                }, contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .clickable(interactionSource = modalInteractionSource, indication = null) { }
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(colorScheme.background, RoundedCornerShape(16.dp))
                    .padding(horizontal = 10.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.check_email), contentDescription = "")
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Проверьте Ваш Email",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = raleway
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Мы отправили код восстановления пароля на вашу электронную почту.",
                    fontSize = 16.sp,
                    color = colorScheme.onSurface,
                    fontFamily = raleway,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}