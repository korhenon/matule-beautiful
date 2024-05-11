package com.example.matulebeautiful.ui.screens.register

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val isPrivacyPolicyChecked: Boolean = false
)
