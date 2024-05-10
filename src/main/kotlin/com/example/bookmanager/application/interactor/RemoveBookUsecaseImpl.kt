package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.RemoveBookUseCase
import com.example.bookmanager.domain.model.BookId
import com.example.bookmanager.domain.repository.BookRepository
import com.example.bookmanager.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RemoveBookUsecaseImpl(
    private val bookRepository: BookRepository
) : RemoveBookUseCase {

    @Transactional
    override fun handle(id: Long): Result<Unit> {
        val bookId = BookId.create(id)
        val book = bookRepository.findById(bookId) ?: return Result.failure(Exception("Book not found: $id"))
        when (bookRepository.delete(book)) {
            0 -> {
                throw NotFoundException("Book not found: $id")
            }
            1 -> {
                return Result.success(Unit)
            }
            else -> {
                throw IllegalStateException("Unexpected delete count. Id: $id")
            }
        }
    }
}
