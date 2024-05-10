package com.example.bookmanager.application.usecase

import com.example.bookmanager.domain.model.Book
import java.time.LocalDate

/**
 * 書籍更新ユースケース
 */
interface ModifyBookUseCase {

    /**
     * 書籍を更新する
     * @param id 書籍ID
     * @param title タイトル(未設定の場合は更新しない)
     * @param isbn ISBN(未設定の場合は更新しない)
     * @param publishedDate 出版日(未設定の場合は更新しない)
     * @param authorId 著者ID(未設定の場合は更新しない)
     */
    fun handle(id: Long, title: String?, isbn: String?, publishedDate: LocalDate?, authorId: Long?)
}