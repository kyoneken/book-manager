package com.example.bookmanager.controller

import com.example.bookmanager.application.usecase.ModifyBookUseCase
import com.example.bookmanager.application.usecase.RegisterBookUseCase
import com.example.bookmanager.application.usecase.RemoveBookUseCase
import com.example.bookmanager.application.usecase.SearchBookUsecase
import com.example.bookmanager.controller.request.ModifyBookRequest
import com.example.bookmanager.controller.request.RegisterBookRequest
import com.example.bookmanager.controller.response.ErrorResponse
import com.example.bookmanager.controller.response.ModifyBookResponse
import com.example.bookmanager.controller.response.RegisterBookResponse
import com.example.bookmanager.controller.response.SearchBookResponse
import com.example.bookmanager.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 書籍情報のコントローラ
 */
@RestController
@RequestMapping("book")
class BookController(
    private val searchBookUseCase: SearchBookUsecase,
    private val registerBookUseCase: RegisterBookUseCase,
    private val modifyBookUseCase: ModifyBookUseCase,
    private val removeBookUseCase: RemoveBookUseCase
) {

    /**
     * 書籍情報の検索
     * @param title タイトル
     * @param authorId 著者ID
     * @return 書籍情報(200: 検索成功、0件の場合も含む)
     * @throws NotFoundException 書籍が見つからない場合
     */
    @GetMapping("/search")
    fun search(
        @RequestParam("title") title: String?,
        @RequestParam("authorId") authorId: Long?
    ): ResponseEntity<List<SearchBookResponse>> {
        val books = searchBookUseCase.handle(title, authorId)
        return ResponseEntity(
            books.map { SearchBookResponse(it.id.value, it.title.value, it.isbn.value, it.publishedDate, it.author.name.toString()) },
            HttpStatus.OK
        )
    }

    /**
     * 書籍情報の登録
     * @param request 書籍情報
     * @return 登録結果(201: 登録成功)
     */
    @PostMapping()
    fun register(@RequestBody request: RegisterBookRequest): ResponseEntity<RegisterBookResponse> {
        val result = registerBookUseCase.handle(
            request.title,
            request.isbn,
            request.publishedDate,
            request.authorId
        )
        return ResponseEntity(
            RegisterBookResponse(result.value),
            HttpStatus.CREATED
        )
    }

    /**
     * 書籍情報の修正
     * @param id 書籍ID
     * @param request 修正情報
     * @return 修正結果(200: 修正成功)
     */
    @PatchMapping("/{id}")
    fun modify(@PathVariable("id") id: Long, @RequestBody request: ModifyBookRequest): ResponseEntity<ModifyBookResponse> {
        modifyBookUseCase.handle(
            id,
            request.title,
            request.isbn,
            request.publishedDate,
            request.authorId
        )
        return ResponseEntity(ModifyBookResponse("Book $id modified successfully."), HttpStatus.OK)
    }

    /**
     * 書籍情報の削除
     * @param id 書籍ID
     * @return 削除結果(200: 削除成功, 409: 既に削除済み)
     */
    @DeleteMapping("/{id}")
    fun remove(@PathVariable("id") id: Long): ResponseEntity<ModifyBookResponse> {
        val result = removeBookUseCase.handle(id)
        return if (result.isSuccess) {
            ResponseEntity(
                ModifyBookResponse("Book $id removed successfully."),
                HttpStatus.OK
            )
        } else {
            ResponseEntity(
                ModifyBookResponse("Book $id is already removed."),
                HttpStatus.CONFLICT
            )
        }
    }

    /**
     * 例外処理(NotFoundException)
     * @param ex 例外 NotFoundException
     * @return エラーレスポンス(404: Not Found)
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ErrorResponse> {
        println(ex.message)
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.message)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    /**
     * 例外処理(IllegalArgumentException)
     * @param ex 例外 IllegalArgumentException
     * @return エラーレスポンス(400: Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        println(ex.message)
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    /**
     * 例外処理(Exception)
     * @param ex 例外 Exception
     * @return エラーレスポンス(500: Internal Server Error)
     */
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        println(ex.message)
        val errorResponse = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
