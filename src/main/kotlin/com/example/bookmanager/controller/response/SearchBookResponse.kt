package com.example.bookmanager.controller.response

import java.time.LocalDate

data class SearchBookResponse(
    val id: Long,
    val title: String,
    val isbn: String,
    val publishedDate: LocalDate,
    val authorName: String
)
