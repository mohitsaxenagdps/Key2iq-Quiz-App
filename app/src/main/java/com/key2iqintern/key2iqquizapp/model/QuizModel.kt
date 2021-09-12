package com.key2iqintern.key2iqquizapp.model

data class QuizModel(
    val id: Int,
    val quesText: String,
    val options: Map<String, Boolean>
)
