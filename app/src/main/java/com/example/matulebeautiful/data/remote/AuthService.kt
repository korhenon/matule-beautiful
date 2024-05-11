package com.example.matulebeautiful.data.remote

import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject

class AuthService @Inject constructor(
    private val auth: Auth
) {
    suspend fun login(email: String, password: String) {
        auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signUp(name: String, email: String, password: String) {
        auth.signUpWith(Email) {
            this.data = JsonObject(mapOf("full_name" to JsonPrimitive(name)))
            this.email = email
            this.password = password
        }
    }

    fun getUserInfo(): UserInfo? {
        return auth.currentUserOrNull()
    }

    suspend fun sendOtp(email: String) {
        auth.signInWith(OTP) {
            this.email = email
        }
    }

    suspend fun verifyOtp(email: String, code: String) {
        auth.verifyEmailOtp(OtpType.Email.EMAIL, email, code)
    }

    suspend fun changePassword(password: String) {
        auth.updateUser {
            this.password = password
        }
    }
}