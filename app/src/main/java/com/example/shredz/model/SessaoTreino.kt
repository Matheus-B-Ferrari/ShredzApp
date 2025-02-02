package com.example.shredz.model

open class SessaoTreino(
    val nome: String,
    val exercicios: MutableList<Exercicio> = mutableListOf()
)
