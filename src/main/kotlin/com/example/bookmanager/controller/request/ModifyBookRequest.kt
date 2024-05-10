package com.example.bookmanager.controller.request

import java.time.LocalDate

data class ModifyBookRequest(
    val title: String? = null,
    val isbn: String? = null,
    val publishedDate: LocalDate? = null,
    val authorId: Long? = null
)
