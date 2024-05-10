package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.FindAuthorUseCase
import com.example.bookmanager.domain.model.Author
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class FindAuthorUseCaseImpl(
    private val authorRepository: AuthorRepository
) : FindAuthorUseCase {
    override fun handle(id: Long): Author? {
        return authorRepository.findById(AuthorId.create(id))
    }
}
