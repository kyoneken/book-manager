package com.example.bookmanager.domain.repository

import com.example.bookmanager.domain.model.Author
import com.example.bookmanager.domain.model.AuthorId

interface AuthorRepository {
    fun findById(id: AuthorId): Author?
    fun selectAll(): List<Author>
}
