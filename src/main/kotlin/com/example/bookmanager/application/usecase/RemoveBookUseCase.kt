package com.example.bookmanager.application.usecase

/**
 * 書籍削除ユースケース
 */
interface RemoveBookUseCase {
    /**
     * 書籍を削除する
     * @param id 書籍ID
     * @return 処理結果
     */
    fun handle(id: Long): Result<Unit>
}
