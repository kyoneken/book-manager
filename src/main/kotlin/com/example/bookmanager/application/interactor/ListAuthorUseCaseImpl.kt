package com.example.bookmanager.application.interactor

import com.example.bookmanager.application.usecase.ListAuthorUseCase
import com.example.bookmanager.domain.model.Author
import com.example.bookmanager.domain.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class ListAuthorUseCaseImpl(
    private val authorRepository: AuthorRepository
): ListAuthorUseCase {
    override fun handle():  List<Author> {
        return authorRepository.selectAll()
    }
}