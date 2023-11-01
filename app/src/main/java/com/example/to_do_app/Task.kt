package com.example.to_do_app

data class Task(
    val title: String,
    var isChecked: Boolean = false
)