package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.SearchBookUsecase
import com.example.bookmanager.domain.model.Book
import com.example.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Component

@Component
class SearchBookUsecaseImpl (
    private val bookRepository: BookRepository
): SearchBookUsecase {
    override fun handle(title: String) : List<Book> {
        return bookRepository.searchBooks(title)
    }
}