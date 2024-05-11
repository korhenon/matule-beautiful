package com.example.matulebeautiful.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matulebeautiful.common.isValidEmail
import com.example.matulebeautiful.domain.navigation.NavDestinations
import com.example.matulebeautiful.ui.components.CustomTextField
import com.example.matulebeautiful.ui.components.GoogleButton
import com.example.matulebeautiful.ui.components.OnBackgroundBackButton
import com.example.matulebeautiful.ui.components.PasswordField
import com.example.matulebeautiful.ui.components.PrimaryMaxWidthButton
import com.example.matulebeautiful.ui.theme.poppins
import com.example.matulebeautiful.ui.theme.raleway

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(23.dp))
            OnBackgroundBackButton(navController)
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = "Привет!",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                fontFamily = raleway,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Заполните Свои данные или продолжите через социальные медиа",
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Email",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                fontFamily = raleway
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                placeholder = "xyz@gmail.com",
                value = viewModel.state.email,
                onValueChanged = { viewModel.state = viewModel.state.copy(email = it) },
                isError = !viewModel.state.email.isValidEmail() && viewModel.state.email != ""
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Пароль",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                fontFamily = raleway
            )
            Spacer(modifier = Modifier.height(12.dp))
            PasswordField(value = viewModel.state.password) {
                viewModel.state = viewModel.state.copy(password = it)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Text(
                    text = "Востановить",
                    color = colorScheme.onSurfaceVariant,
                    fontFamily = poppins,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(NavDestinations.ForgotPassword)
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryMaxWidthButton(text = "Войти") {
                viewModel.login(navController)
            }
            Spacer(modifier = Modifier.height(24.dp))
            GoogleButton(navController, viewModel.composeAuth)
        }
        Row(Modifier.padding(bottom = 47.dp)) {
            Text(
                text = "Вы впервые? ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = raleway,
                color = colorScheme.onSurfaceVariant
            )
            Text(
                text = "Создать пользователя",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = raleway,
                color = colorScheme.onBackground,
                modifier = Modifier.clickable {
                    navController.navigate(NavDestinations.Register)
                }
            )
        }
    }
}