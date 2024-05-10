package com.example.bookmanager.domain.repository

import com.example.bookmanager.domain.model.Author
import com.example.bookmanager.domain.model.Book
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class MockBookRepositoryImpl: BookRepository {

    private val books = mutableListOf(
        Book(1L, "Book 1", "1234567890", LocalDate.now(), Author(1L, "Author 1", "author1@example.com", emptyList())),
        Book(2L, "Book 2", "0987654321", LocalDate.now(), Author(2L, "Author 2", "author2@example.com", emptyList())),
        Book(3L, "Book 3", "1122334455", LocalDate.now(), Author(1L, "Author 1", "author1@example.com", emptyList()))
    )
    override fun searchBooks(title: String): List<Book> {
        return books.filter { it.title.contains(title) }
    }
}