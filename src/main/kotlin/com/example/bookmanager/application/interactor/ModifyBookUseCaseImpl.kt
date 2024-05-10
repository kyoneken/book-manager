package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.ModifyBookUseCase
import com.example.bookmanager.domain.model.*
import com.example.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ModifyBookUseCaseImpl(
    val bookRepository: BookRepository,
): ModifyBookUseCase {

    @Transactional
    override fun handle(id: Long, title: String?, isbn: String?, publishedDate: LocalDate?, authorId: Long?) {
        val bookId = BookId.create(id)
        val temp = bookRepository.findById(bookId)
        if (temp == null) {
            val message = "Book not found: $id"
            println(message)
            throw IllegalArgumentException(message)
        }

        val updatedTitle = title?.let { BookTitle.tryCreate(it) }
        val updatedIsbn = isbn?.let { ISBN.tryCreate(it) }
        val updatedAuthorId = authorId?.let { AuthorId.tryCreate(it) }

        when (bookRepository.updateById(
            bookId,
            updatedTitle,
            updatedIsbn,
            publishedDate,
            updatedAuthorId
        )){
            1 -> return
            else -> {
                val message = "Unexpected update count. Id: $id"
                println(message)
                throw IllegalStateException(message)
            }
        }
    }
}