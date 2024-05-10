package com.example.bookmanager.application.usecase

import com.example.bookmanager.domain.model.Book

interface SearchBookUsecase {
    fun handle(title: String): List<Book>
}