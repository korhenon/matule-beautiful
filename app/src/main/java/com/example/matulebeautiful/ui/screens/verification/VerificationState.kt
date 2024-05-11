package com.example.matulebeautiful.ui.screens.verification

enum class PasswordGenerationState {
    Phrase, Password
}

data class VerificationState(
    val email: String = "",
    val time: Int = 60,
    val isModalOpen: Boolean = false,
    val values: List<String> = listOf("", "", "", "", "", ""),
    val isError: Boolean = false,
    val phrase: String = "",
    val password: String = "",
    val passwordGenerationState: PasswordGenerationState = PasswordGenerationState.Phrase
)