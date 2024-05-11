package com.example.matulebeautiful.domain.repository

import com.example.matulebeautiful.data.models.Response
import com.example.matulebeautiful.data.remote.AuthService
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    val service: AuthService
) {
    private suspend fun invoke(func: suspend () -> Unit): Response<Boolean> {
        return try {
            func()
            Response(true)
        } catch (_: BadRequestRestException) {
            Response(false, "Неверные данные")
        } catch (_: HttpRequestException) {
            Response(false, "Нет интернета")
        }
    }

    suspend fun login(email: String, password: String): Response<Boolean> {
        return invoke {
            service.login(email, password)
        }
    }

    suspend fun register(name: String, email: String, password: String): Response<Boolean> {
        return invoke {
            service.signUp(name, email, password)
        }
    }

    suspend fun sendOtp(email: String): Response<Boolean> {
        return invoke {
            service.sendOtp(email)
        }
    }

    suspend fun checkOtp(email: String, code: String): Response<Boolean> {
        return invoke {
            service.verifyOtp(email, code)
        }
    }

    suspend fun changePassword(newPassword: String): Response<Boolean> {
        return invoke {
            service.changePassword(newPassword)
        }
    }
}