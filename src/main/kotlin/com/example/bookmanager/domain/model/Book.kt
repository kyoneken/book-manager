package com.example.bookmanager.domain.model

import java.time.LocalDate

/**
 * 書籍情報
 * @property id ID
 * @property title タイトル
 * @property isbn ISBN
 * @property publishedDate 出版日
 * @property author 著者情報
 */
data class Book(
    val id: Long,
    val title: String,
    val isbn: String,
    val publishedDate: LocalDate,
    val author: Author
)
