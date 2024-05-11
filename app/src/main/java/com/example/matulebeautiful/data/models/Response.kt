package com.example.matulebeautiful.data.models

data class Response<T> (
    val value: T,
    val error: String = ""
)