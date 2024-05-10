package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.RegisterBookUseCase
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.BookId
import com.example.bookmanager.domain.model.BookTitle
import com.example.bookmanager.domain.model.ISBN
import com.example.bookmanager.domain.model.NewBook
import com.example.bookmanager.domain.repository.BookRepository
import com.example.bookmanager.domain.service.BookService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class RegisterBookUseCaseImpl(
    private val bookService: BookService,
    private val bookRepository: BookRepository
) : RegisterBookUseCase {

    @Transactional
    override fun handle(title: String, isbn: String, publishedDate: LocalDate, authorId: Long): BookId {
        val newBook = NewBook(BookTitle.create(title), ISBN.create(isbn), publishedDate, AuthorId.create(authorId))
        if (bookService.exists(newBook.isbn)) {
            val message = "ISBN already exists: ${newBook.isbn}"
            println(message)
            throw IllegalArgumentException(message)
        }
        if (!bookService.exists(newBook.authorId)) {
            val message = "Author not found: ${newBook.authorId}"
            println(message)
            throw IllegalArgumentException(message)
        }

        return bookRepository.insert(newBook)
    }
}
