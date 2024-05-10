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
    val id: BookId,
    val title: BookTitle,
    val isbn: ISBN,
    val publishedDate: LocalDate,
    val author: Author
)

/**
 * 新規書籍情報
 * @property title タイトル
 * @property isbn ISBN
 * @property publishedDate 出版日
 * @property authorId 著者ID
 */
data class NewBook(
    val title: BookTitle,
    val isbn: ISBN,
    val publishedDate: LocalDate,
    val authorId: AuthorId
)

/**
 * 書籍更新情報
 * @property id ID
 * @property title タイトル
 * @property isbn ISBN
 * @property publishedDate 出版日
 * @property authorId 著者ID
 */
data class UpdateBook(
    val id: BookId,
    val title: BookTitle?,
    val isbn: ISBN?,
    val publishedDate: LocalDate?,
    val authorId: AuthorId?
)

/**
 * 書籍ID
 * @property value ID
 */
data class BookId private constructor(val value: Long) {
    companion object {
        fun create(value: Long): BookId {
            validate(value)
            return BookId(value)
        }

        private fun validate(value: Long) {
            require(value > 0) { "Book ID must be a positive number" }
        }
    }
}

/**
 * 書籍タイトル
 * @property value タイトル
 */
data class BookTitle private constructor(val value: String) {
    companion object {
        fun create(value: String): BookTitle {
            validate(value)
            return BookTitle(value)
        }

        fun tryCreate(value: String?): BookTitle? {
            return value?.let {
                try {
                    create(it)
                } catch (e: IllegalArgumentException) {
                    null
                }
            }
        }

        private fun validate(value: String) {
            require(value.isNotBlank()) { "Book title must not be blank" }
            require(value.length <= 255) { "Book title must not exceed 200 characters" }
        }
    }
}

/**
 * ISBN
 * @property value ISBN
 */
data class ISBN private constructor(val value: String) {
    companion object {
        fun create(value: String): ISBN {
            validate(value)
            return ISBN(value)
        }

        fun tryCreate(value: String?): ISBN? {
            return value?.let {
                try {
                    create(it)
                } catch (e: IllegalArgumentException) {
                    null
                }
            }
        }

        private fun validate(value: String) {
            require(value.isNotBlank()) { "ISBN must not be blank" }
            require(value.length == 13) { "ISBN must be 13 characters" }
            require(value.matches(Regex("""\d{13}"""))) { "ISBN must be a number" }
        }
    }
}
