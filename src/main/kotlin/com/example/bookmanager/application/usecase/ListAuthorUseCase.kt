package com.example.bookmanager.application.usecase

import com.example.bookmanager.domain.model.Author

/**
 * 著者一覧取得ユースケース
 */
interface ListAuthorUseCase {

    /**
     * 著者一覧を取得する
     * @return 著者一覧(著者がいない場合は空リスト)
     */
    fun handle(): List<Author>
}