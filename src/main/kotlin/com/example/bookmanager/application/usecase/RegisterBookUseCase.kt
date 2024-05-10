package com.example.bookmanager.application.usecase

import com.example.bookmanager.domain.model.BookId
import java.time.LocalDate

/**
 * 書籍登録ユースケース
 */
interface RegisterBookUseCase {

    /**
     * 書籍を登録する
     * @param title タイトル
     * @param isbn ISBN
     * @param publishedDate 出版日
     * @param authorId 著者ID
     * @return 登録した書籍
     */
    fun handle(title: String, isbn: String, publishedDate: LocalDate, authorId: Long): BookId
}
