package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.ModifyBookUseCase
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.BookId
import com.example.bookmanager.domain.model.BookTitle
import com.example.bookmanager.domain.model.ISBN
import com.example.bookmanager.domain.model.UpdateBook
import com.example.bookmanager.domain.repository.BookRepository
import com.example.bookmanager.domain.service.BookService
import com.example.bookmanager.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ModifyBookUseCaseImpl(
    val bookService: BookService,
    val bookRepository: BookRepository
) : ModifyBookUseCase {

    @Transactional
    override fun handle(id: Long, title: String?, isbn: String?, publishedDate: LocalDate?, authorId: Long?) {
        val bookId = BookId.create(id)
        val persistedBook = bookRepository.findById(bookId) ?: throw NotFoundException("Book not found: $id")

        val updatedTitle = title?.let { BookTitle.create(it) }
        val updatedIsbn = isbn?.let { ISBN.create(it) }
        val updatedAuthorId = authorId?.let { AuthorId.create(it) }

        if (updatedIsbn != null && updatedIsbn != persistedBook.isbn) {
            if (bookService.exists(updatedIsbn)) {
                throw IllegalArgumentException("ISBN already exists: $updatedIsbn")
            }
        }
        if (updatedAuthorId != null && !bookService.exists(updatedAuthorId)) {
            throw IllegalArgumentException("Author not found: $updatedAuthorId")
        }
        val updateBook = UpdateBook(
            id = bookId,
            title = updatedTitle,
            isbn = updatedIsbn,
            publishedDate = publishedDate,
            authorId = updatedAuthorId
        )

        val result = bookRepository.update(updateBook)
        when (result) {
            1 -> return
            else -> {
                throw IllegalStateException("Unexpected update count. Id: $id")
            }
        }
    }
}
