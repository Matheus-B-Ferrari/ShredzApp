package com.example.shredz.model

class Rotina(
    nome: String,
    exercicios: MutableList<Exercicio> = mutableListOf(),
    var duracao: Long = 0, // Duração total da rotina em minutos
    var volumeTotal: Float = 0f,
    var repeticoesTotais: Int = 0
) : SessaoTreino(nome, exercicios)
