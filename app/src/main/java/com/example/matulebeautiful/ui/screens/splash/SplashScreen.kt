package com.example.matulebeautiful.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matulebeautiful.ui.theme.raleway

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    LaunchedEffect(true) {
        viewModel.navigate(navController)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(colorScheme.primary, colorScheme.secondary))),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = "MATULE",
                fontSize = 65.sp,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onPrimary,
                fontFamily = raleway
            )
            Text(
                text = " ME",
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                color = colorScheme.onPrimary,
                fontFamily = raleway
            )
        }
    }

}