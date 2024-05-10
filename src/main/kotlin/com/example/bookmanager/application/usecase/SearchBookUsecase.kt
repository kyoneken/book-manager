package com.example.bookmanager.application.usecase

import com.example.bookmanager.domain.model.Book

/**
 * 書籍検索ユースケース
 */
interface SearchBookUsecase {
    /**
     * 書籍を検索する
     * @param title タイトル(未設定の場合は検索条件に含めない)
     * @param authorId 著者ID(未設定の場合は検索条件に含めない)
     * @return 条件に合致する書籍一覧(条件のない場合は全書籍、該当なしの場合は空リスト)
     */
    fun handle(title: String?, authorId: Long?): List<Book>
}