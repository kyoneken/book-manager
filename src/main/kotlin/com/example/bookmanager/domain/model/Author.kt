package com.example.bookmanager.domain.model

import java.time.LocalDate

/**
 * 著者情報
 * @property id ID
 * @property name 名前
 * @property email メールアドレス
 */
data class Author(
    val id: AuthorId,
    val name: AuthorName,
    val email: Email
)

/**
 * 著者ID
 * @property value ID
 */
data class AuthorId private constructor(val value: Long) {
    companion object {
        fun create(value: Long): AuthorId {
            validate(value)
            return AuthorId(value)
        }

        fun tryCreate(value: Long?): AuthorId? {
            return value?.let {
                try {
                    create(it)
                } catch (e: IllegalArgumentException) {
                    null
                }
            }
        }

        private fun validate(value: Long) {
            require(value > 0) { "Author ID must be a positive number" }
        }
    }
}

/**
 * 著者名
 * @property lastName 姓
 * @property firstName 名
 * @constructor 著者名 (姓, 名)
 */
data class AuthorName private constructor(
    /* 姓 */
    val lastName: String,
    /* 名 */
    val firstName: String,
) {
    companion object {
        fun create(fullName: String): AuthorName {
            val parts = fullName.split(" ")
            require(parts.size == 2) { "Author name must be in the format 'firstName lastName'" }
            validate(parts[0], parts[1])
            return AuthorName(parts[0], parts[1])
        }

        private fun validate(lastName: String, firstName: String) {
            require(lastName.isNotBlank()) { "Last name must not be blank" }
            require(firstName.isNotBlank()) { "First name must not be blank" }
        }
    }

    override fun toString(): String {
        return "$lastName $firstName"
    }
}

/**
 * メールアドレス
 * @property value メールアドレス
 */
data class Email private constructor(val value: String) {
    companion object {
        fun create(value: String): Email {
            validate(value)
            return Email(value)
        }

        private fun validate(value: String) {
            require(value.isNotBlank()) { "Email must not be blank" }
            require(value.length <= 255) { "Email must not exceed 255 characters" }
            require(value.matches(Regex("""[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"""))) { "Email must be a valid email address" }
        }
    }
}