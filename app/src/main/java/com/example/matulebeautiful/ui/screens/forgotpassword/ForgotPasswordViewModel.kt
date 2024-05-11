package com.example.matulebeautiful.ui.screens.forgotpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulebeautiful.common.isValidEmail
import com.example.matulebeautiful.domain.repository.AuthRepository
import com.example.matulebeautiful.ui.base.Toaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val toaster: Toaster
) : ViewModel() {
    fun sendOtp() {
        if (state.email.isValidEmail()) {
            viewModelScope.launch {
                val response = repository.sendOtp(state.email)
                state = state.copy(isModalOpen = response.value)
                if (!response.value) {
                    toaster.show(response.error)
                }
            }
        }
    }

    var state by mutableStateOf(ForgotPasswordState())
}