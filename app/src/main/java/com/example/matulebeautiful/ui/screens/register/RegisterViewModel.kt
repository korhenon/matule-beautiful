package com.example.matulebeautiful.ui.screens.register

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
import io.github.jan.supabase.compose.auth.ComposeAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val toaster: Toaster,
    val composeAuth: ComposeAuth
) : ViewModel() {
    fun register(navController: NavController) {
        if (state.isPrivacyPolicyChecked) {
            viewModelScope.launch {
                val response = repository.register(state.name, state.email, state.password)
                if (response.value) {
                    navController.navigate(NavDestinations.Login)
                }
                else {
                    toaster.show(response.error)
                }
            }
        }
    }

    var state by mutableStateOf(RegisterState())
}