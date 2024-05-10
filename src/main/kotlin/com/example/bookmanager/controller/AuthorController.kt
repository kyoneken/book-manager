package com.example.bookmanager.controller

import com.example.bookmanager.application.usecase.FindAuthorUseCase
import com.example.bookmanager.application.usecase.ListAuthorUseCase
import com.example.bookmanager.application.usecase.RegisterAuthorUseCase
import com.example.bookmanager.controller.request.RegisterAuthorRequest
import com.example.bookmanager.controller.response.AuthorResponse
import com.example.bookmanager.controller.response.RegisterBookResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 著者情報のコントローラ
 */
@RestController
@RequestMapping("author")
class AuthorController(
    private val listAuthorUseCase: ListAuthorUseCase,
    private val findAuthorUseCase: FindAuthorUseCase,
    private val registerAuthorUseCase: RegisterAuthorUseCase
) {

    /**
     * 著者情報の一覧取得
     * @return 著者情報(200: 取得成功、0件の場合も含む)
     */
    @GetMapping()
    fun getAuthors(): ResponseEntity<List<AuthorResponse>> {
        val authors = listAuthorUseCase.handle()
        return ResponseEntity.ok(
            authors.map {
                AuthorResponse(
                    it.id.value,
                    it.name.toString(),
                    it.email.value
                )
            }
        )
    }

    /**
     * 著者情報の取得
     * @param id 著者ID
     * @return 著者情報(200: 取得成功, 404: 著者が見つからない)
     */
    @GetMapping("/{id}")
    fun getAuthor(@PathVariable("id") id: Long): ResponseEntity<AuthorResponse> {
        return when (val author = findAuthorUseCase.handle(id)) {
            null -> ResponseEntity.notFound().build()
            else -> ResponseEntity.ok(
                AuthorResponse(
                    author.id.value,
                    author.name.toString(),
                    author.email.value
                )
            )
        }
    }

    /**
     * 著者情報の登録
     * @param request 著者情報
     * @return 登録結果(201: 登録成功)
     */
    @PostMapping()
    fun register(@RequestBody request: RegisterAuthorRequest): ResponseEntity<RegisterBookResponse> {
        val result = registerAuthorUseCase.handle(
            request.name,
            request.email
        )
        return ResponseEntity(
            RegisterBookResponse(result.value),
            HttpStatus.CREATED
        )
    }
}
