package com.example.bookmanager.domain.repository

import com.example.bookmanager.domain.model.Book

interface BookRepository {
    fun searchBooks(title: String): List<Book>
}