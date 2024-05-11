package com.example.matulebeautiful.ui.screens.verification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulebeautiful.common.generatePassword
import com.example.matulebeautiful.domain.repository.AuthRepository
import com.example.matulebeautiful.ui.base.Toaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val toaster: Toaster
) : ViewModel() {
    fun checkOtp() {
        viewModelScope.launch {
            val response = repository.checkOtp(state.email, state.values.joinToString(""))
            state = state.copy(isError = !response.value)
            if (response.value) {
                state = state.copy(isModalOpen = true)
            } else if (response.error == "Нет интернета") toaster.show(response.error)
        }
    }

    fun resendOtp() {
        if (state.time == 0) {
            state = state.copy(time = 60)
        }
    }

    fun init(email: String) {
        state = VerificationState(email = email)
    }

    fun generatePassword() {
        viewModelScope.launch {
            val password = state.phrase.generatePassword()
            val response = repository.changePassword(password)
            if (response.value) {
                state = state.copy(
                    password = password,
                    passwordGenerationState = PasswordGenerationState.Password
                )
            } else {
                toaster.show(response.error)
            }
        }
    }

    var state by mutableStateOf(VerificationState())
}