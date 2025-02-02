package com.example.shredz.model

data class User(
    val nome: String,
    var fotoPerfil: String? = null, // Caminho para a foto
    var peso: Float, // Peso atual do usuário
    val treinamentos: MutableList<Treinamento> = mutableListOf(),
    val rotinas: MutableList<Rotina> = mutableListOf()
)
