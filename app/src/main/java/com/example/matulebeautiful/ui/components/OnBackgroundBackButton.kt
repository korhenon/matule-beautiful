package com.example.matulebeautiful.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matulebeautiful.R

@Composable
fun OnBackgroundBackButton(navController: NavController) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(colorScheme.surface, CircleShape)
            .clickable {
                navController.popBackStack()
            }, contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.arrow_left), contentDescription = "")
    }
}