package com.example.matulebeautiful.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.matulebeautiful.domain.navigation.NavDestinations
import com.example.matulebeautiful.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    suspend fun navigate(navController: NavController) {
        delay(500)
        if (repository.service.getUserInfo() == null) {
            navController.navigate(NavDestinations.Login) {
                popUpTo(NavDestinations.Splash) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(NavDestinations.Home) {
                popUpTo(NavDestinations.Splash) {
                    inclusive = true
                }
            }
        }

    }
}