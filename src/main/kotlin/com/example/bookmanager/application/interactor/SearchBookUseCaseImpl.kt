package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.SearchBookUsecase
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.Book
import com.example.bookmanager.domain.model.BookTitle
import com.example.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class SearchBookUseCaseImpl(
    private val bookRepository: BookRepository
) : SearchBookUsecase {
    override fun handle(title: String?, authorId: Long?): List<Book> {
        val bookTitle = title?.let { BookTitle.create(it) }
        val bookAuthorId = authorId?.let { AuthorId.create(it) }
        return bookRepository.findByIndexOrAll(bookTitle, bookAuthorId)
    }
}
