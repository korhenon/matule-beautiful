package com.example.matulebeautiful.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matulebeautiful.R
import com.example.matulebeautiful.domain.navigation.NavDestinations
import com.example.matulebeautiful.ui.theme.raleway
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle

@Composable
fun GoogleButton(navController: NavController, composeAuth: ComposeAuth) {
    val authState = composeAuth.rememberSignInWithGoogle(
        onResult = {
            if (it == NativeSignInResult.Success) {
                navController.navigate(NavDestinations.Home)
            }
        }
    )

    Button(
        onClick = {
            authState.startFlow()
        },
        Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Image(painter = painterResource(id = R.drawable.google), contentDescription = "")
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "Войти с Google",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            fontFamily = raleway
        )
    }
}