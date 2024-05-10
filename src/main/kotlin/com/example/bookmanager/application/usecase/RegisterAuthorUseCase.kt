package com.example.bookmanager.application.usecase

import com.example.bookmanager.domain.model.AuthorId

/**
 * 著者登録ユースケース
 */
interface RegisterAuthorUseCase {

    /**
     * 著者を登録する
     * @param name 著者の名前
     * @param email 著者のメールアドレス
     * @return AuthorId 登録した著者のID
     */
    fun handle(name: String, email: String): AuthorId
}
