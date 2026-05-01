package com.example.mysawit.model

data class Habit(
    val id: String,
    val name: String,
    val desc: String,
    val goal: Int,
    var progress: Int,
    val unit: String,
    val iconName: String
)