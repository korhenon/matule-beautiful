package com.example.matulebeautiful.common

fun String.isValidEmail(): Boolean {
    return Regex("""^[\w-\.]+@(([\w-]+\.)+[\w-]{2,4}){1,3}$""").matches(this)
}