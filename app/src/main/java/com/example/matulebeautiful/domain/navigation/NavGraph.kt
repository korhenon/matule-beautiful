package com.example.matulebeautiful.domain.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matulebeautiful.ui.screens.forgotpassword.ForgotPasswordScreen
import com.example.matulebeautiful.ui.screens.login.LoginScreen
import com.example.matulebeautiful.ui.screens.register.RegisterScreen
import com.example.matulebeautiful.ui.screens.splash.SplashScreen
import com.example.matulebeautiful.ui.screens.verification.VerificationScreen
import com.example.matulebeautiful.ui.screens.verification.VerificationViewModel

@Composable
fun NavGraph(
    verificationViewModel: VerificationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavDestinations.Splash) {
        composable(NavDestinations.Splash) {
            SplashScreen(navController)
        }
        composable(NavDestinations.Onboarding) {

        }
        composable(NavDestinations.Login) {
            LoginScreen(navController)
        }
        composable(NavDestinations.Register) {
            RegisterScreen(navController)
        }
        composable(NavDestinations.ForgotPassword) {
            ForgotPasswordScreen(navController, verificationViewModel)
        }
        composable(NavDestinations.Verification) {
            VerificationScreen(navController, verificationViewModel)
        }
        composable(NavDestinations.Home) {

        }
    }
}