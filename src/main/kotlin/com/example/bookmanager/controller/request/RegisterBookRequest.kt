package com.example.bookmanager.controller.request

import java.time.LocalDate

data class RegisterBookRequest(
    val title: String,
    val isbn: String,
    val publishedDate: LocalDate,
    val authorId: Long
)
