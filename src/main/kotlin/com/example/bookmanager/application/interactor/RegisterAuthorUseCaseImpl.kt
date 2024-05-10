package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.RegisterAuthorUseCase
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.AuthorName
import com.example.bookmanager.domain.model.Email
import com.example.bookmanager.domain.model.NewAuthor
import com.example.bookmanager.domain.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class RegisterAuthorUseCaseImpl(
    private val authorRepository: AuthorRepository
) : RegisterAuthorUseCase {
    override fun handle(name: String, email: String): AuthorId {
        val newAuthor = NewAuthor(AuthorName.create(name), Email.create(email))
        return authorRepository.insert(newAuthor)
    }
}
