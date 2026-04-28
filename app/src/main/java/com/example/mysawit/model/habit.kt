package com.example.mysawit.model

data class Habit(
    val name: String,
    val desc: String,
    val goal: Int,
    var progress: Int,
    val unit: String
)