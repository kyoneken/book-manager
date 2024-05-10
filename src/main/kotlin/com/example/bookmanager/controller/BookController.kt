package com.example.bookmanager.controller

import com.example.bookmanager.application.usecase.SearchBookUsecase
import com.example.bookmanager.controller.request.RegisterBookRequest
import com.example.bookmanager.domain.model.Book
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
class BookController (
    private val searchBookUsecase: SearchBookUsecase,
) {
    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/search")
    fun search(@RequestParam("title") title: String): List<Book> {
        return searchBookUsecase.handle(title)
    }
}