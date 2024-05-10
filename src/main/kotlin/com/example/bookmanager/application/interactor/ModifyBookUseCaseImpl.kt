package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.ModifyBookUseCase
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.BookId
import com.example.bookmanager.domain.model.BookTitle
import com.example.bookmanager.domain.model.ISBN
import com.example.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ModifyBookUseCaseImpl(
    val bookRepository: BookRepository
) : ModifyBookUseCase {

    @Transactional
    override fun handle(id: Long, title: String?, isbn: String?, publishedDate: LocalDate?, authorId: Long?) {
        val bookId = BookId.create(id)
        val persistedBook = bookRepository.findById(bookId)
        if (persistedBook == null) {
            val message = "Book not found: $id"
            println(message)
            throw IllegalArgumentException(message)
        }

        val updatedTitle = title?.let { BookTitle.create(it) }
        val updatedIsbn = isbn?.let { ISBN.create(it) }
        val updatedAuthorId = authorId?.let { AuthorId.create(it) }
        val result = bookRepository.updateById(
            bookId,
            updatedTitle,
            updatedIsbn,
            publishedDate,
            updatedAuthorId
        )
        when (result) {
            1 -> return
            else -> {
                val message = "Unexpected update count. Id: $id"
                println(message)
                throw IllegalStateException(message)
            }
        }
    }
}
