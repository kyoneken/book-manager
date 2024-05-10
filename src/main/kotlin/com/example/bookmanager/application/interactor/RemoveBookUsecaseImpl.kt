package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.RemoveBookUseCase
import com.example.bookmanager.domain.model.BookId
import com.example.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RemoveBookUsecaseImpl(
    private val bookRepository: BookRepository,
): RemoveBookUseCase {

    @Transactional
    override fun handle(id: Long): Result<Unit> {
        when (bookRepository.deleteById(BookId.create(id))) {
            0 -> {
                // TODO: ロガーを使ってwarnログを出力する
                val message = "Book not found: $id"
                println(message)
                return Result.failure(IllegalArgumentException(message))
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