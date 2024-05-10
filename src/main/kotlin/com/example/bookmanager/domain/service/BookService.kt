package com.example.bookmanager.domain.service

import com.example.bookmanager.domain.constraint.ForeignKeyConstraintCheck
import com.example.bookmanager.domain.constraint.UniqueConstraintCheck
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.ISBN
import com.example.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Component

/**
 * 書籍情報ドメインサービス
 */
@Component
class BookService(
    private val bookRepository: BookRepository,
    private val isbnCheck: UniqueConstraintCheck<ISBN>,
    private val authorCheck: ForeignKeyConstraintCheck<AuthorId>
) {

    /**
     * ISBN存在チェック
     */
    fun exists(isbn: ISBN): Boolean {
        return isbnCheck.exists(isbn)
    }

    fun exists(authorId: AuthorId): Boolean {
        return authorCheck.exists(authorId)
    }
}
