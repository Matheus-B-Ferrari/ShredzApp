package com.example.shredz.model

data class ExercicioHistorico(
    val data: String,     // Data do treino
    val nomeExercicio: String, // Nome do exercício (ex: Supino)
    val peso: Float,       // Peso levantado no exercício
    val repeticoes: Int,   // Repetições feitas
    val volume: Float      // Peso * Repetições
)

