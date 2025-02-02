package com.example.shredz.model

data class Set(
    val peso: Float,
    val repeticoes: Int
) {
    val volume: Float
        get() = peso * repeticoes
}
