package com.example.bookmanager.domain.model

import java.time.LocalDate

/**
 * 著者情報
 * @property id ID
 * @property name 名前
 * @property email メールアドレス
 * @property books 書籍情報
 */
data class Author(
    val id: Long,
    val name: String,
    val email: String,
    val books: List<Book>
)
