package com.example.shredz.model

data class Exercicio(
    val nome: String,
    val sets: MutableList<Set> = mutableListOf(),
    var maiorPeso: Float = 0f,
    var maiorVolume: Float = 0f,
    var historico: MutableList<ExercicioHistorico> = mutableListOf()
)
