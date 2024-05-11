package com.example.matulebeautiful.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.matulebeautiful.domain.navigation.NavDestinations
import com.example.matulebeautiful.domain.repository.AuthRepository
import com.example.matulebeautiful.ui.base.Toaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val toaster: Toaster
): ViewModel() {
    fun login(navController: NavController) {
        viewModelScope.launch {
            val response = repository.login(state.email, state.password)
            if (response.value) {
                navController.navigate(NavDestinations.Home)
            } else {
                toaster.show(response.error)
            }
        }
    }

    var state by mutableStateOf(LoginState())
}