package com.example.matulebeautiful.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matulebeautiful.R
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
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
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
                text = "Регистрация",
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
                text = "Ваше имя",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                fontFamily = raleway
            )
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(
                placeholder = "xxxxxxxx",
                value = viewModel.state.name,
                onValueChanged = { viewModel.state = viewModel.state.copy(name = it) }
            )
            Spacer(modifier = Modifier.height(12.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .background(colorScheme.surface, RoundedCornerShape(6.dp))
                        .clickable {
                            viewModel.state =
                                viewModel.state.copy(isPrivacyPolicyChecked = !viewModel.state.isPrivacyPolicyChecked)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.state.isPrivacyPolicyChecked) {
                        Image(
                            painter = painterResource(id = R.drawable.privacy_checked),
                            contentDescription = ""
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Даю согласие на обработку персональных данных",
                    color = colorScheme.onSurface,
                    fontFamily = raleway,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryMaxWidthButton(text = "Зарегистрироваться") {
                viewModel.register(navController)
            }
            Spacer(modifier = Modifier.height(24.dp))
            GoogleButton(navController, viewModel.composeAuth)
        }
        Row(Modifier.padding(bottom = 47.dp)) {
            Text(
                text = "Есть аккаунт? ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = raleway,
                color = colorScheme.onSurfaceVariant
            )
            Text(
                text = "Войти",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = raleway,
                color = colorScheme.onBackground,
                modifier = Modifier.clickable {
                    navController.navigate(NavDestinations.Login)
                }
            )
        }
    }
}