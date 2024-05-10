package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.RegisterBookUseCase
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.Book
import com.example.bookmanager.domain.model.BookTitle
import com.example.bookmanager.domain.model.ISBN
import com.example.bookmanager.domain.model.NewBook
import com.example.bookmanager.domain.repository.AuthorRepository
import com.example.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class RegisterBookUseCaseImpl(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
) : RegisterBookUseCase {

    @Transactional
    override fun handle(title: String, isbn: String, publishedDate: LocalDate, authorId: Long): Book {
        val author = authorRepository.findById(AuthorId.create(authorId)) ?: throw IllegalArgumentException("著者が見つかりません")
        val newBook = NewBook(BookTitle.create(title), ISBN.create(isbn), publishedDate, author.id)
        val newBookId = bookRepository.insert(newBook)

        return Book(
            newBookId,
            newBook.title,
            newBook.isbn,
            newBook.publishedDate,
            author
        )
    }
}
