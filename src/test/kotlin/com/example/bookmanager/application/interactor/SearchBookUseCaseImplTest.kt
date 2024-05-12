package com.example.bookmanager.application.interactor

import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.Book
import com.example.bookmanager.domain.model.BookTitle
import com.example.bookmanager.domain.repository.BookRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

/**
 * Test class for SearchBookUseCaseImpl.
 * @see SearchBookUseCaseImpl
 * @property bookRepository BookRepository
 */
class SearchBookUseCaseImplTest : BehaviorSpec({
    given("SearchBookUseCaseImpl") {
        val bookRepository = mockk<BookRepository>()
        val searchBookUseCase = SearchBookUseCaseImpl(bookRepository)

        `when`("handle with title and authorId") {
            val title = "Kotlin"
            val authorId = 1L
            val expectedBooks = listOf(mockk<Book>(), mockk<Book>())
            every {
                bookRepository.findByIndexOrAll(BookTitle.create(title), AuthorId.create(authorId))
            } returns expectedBooks

            then("should return book list matching title and authorId") {
                val result = searchBookUseCase.handle(title, authorId)
                result shouldBe expectedBooks
                verify(exactly = 1) {
                    bookRepository.findByIndexOrAll(BookTitle.create(title), AuthorId.create(authorId))
                }
            }
        }

        `when`("handle with null title") {
            val authorId = 1L
            val expectedBooks = listOf(mockk<Book>(), mockk<Book>())
            every {
                bookRepository.findByIndexOrAll(null, AuthorId.create(authorId))
            } returns expectedBooks

            then("should return books matching authorId") {
                val result = searchBookUseCase.handle(null, authorId)
                result shouldBe expectedBooks
                verify(exactly = 1) {
                    bookRepository.findByIndexOrAll(null, AuthorId.create(authorId))
                }
            }
        }

        `when`("handle with null authorId") {
            val title = "Kotlin"
            val expectedBooks = listOf(mockk<Book>(), mockk<Book>())
            every {
                bookRepository.findByIndexOrAll(BookTitle.create(title), null)
            } returns expectedBooks

            then("should return books matching title") {
                val result = searchBookUseCase.handle(title, null)
                result shouldBe expectedBooks
                verify(exactly = 1) {
                    bookRepository.findByIndexOrAll(BookTitle.create(title), null)
                }
            }
        }

        `when`("handle with null title and authorId") {
            val expectedBooks = listOf(mockk<Book>(), mockk<Book>())
            every {
                bookRepository.findByIndexOrAll(null, null)
            } returns expectedBooks

            then("should return all books") {
                val result = searchBookUseCase.handle(null, null)
                result shouldBe expectedBooks
                verify(exactly = 1) {
                    bookRepository.findByIndexOrAll(null, null)
                }
            }
        }
    }
})
