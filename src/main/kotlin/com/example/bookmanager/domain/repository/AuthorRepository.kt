package com.example.bookmanager.domain.repository

import com.example.bookmanager.domain.model.Author
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.NewAuthor

interface AuthorRepository {
    fun findById(id: AuthorId): Author?
    fun selectAll(): List<Author>

    fun insert(newBook: NewAuthor): AuthorId
}
