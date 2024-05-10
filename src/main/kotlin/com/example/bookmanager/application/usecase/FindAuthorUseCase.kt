package com.example.bookmanager.application.usecase

import com.example.bookmanager.domain.model.Author

/**
 * 著者取得ユースケース
 */
interface FindAuthorUseCase {

    /**
     * 著者を取得する
     * @param id 著者ID
     * @return 著者情報(該当なしの場合はnull)
     */
    fun handle(id: Long): Author?
}
