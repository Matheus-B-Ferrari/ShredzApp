package com.example.shredz.model

class Treinamento (
    nome: String,
    exercicios: MutableList<Exercicio> = mutableListOf(),
    val data: String // Data em que o treino foi realizado
) : SessaoTreino(nome, exercicios)