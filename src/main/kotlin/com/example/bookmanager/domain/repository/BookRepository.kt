package com.example.bookmanager.domain.repository

import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.Book
import com.example.bookmanager.domain.model.BookId
import com.example.bookmanager.domain.model.BookTitle
import com.example.bookmanager.domain.model.NewBook
import com.example.bookmanager.domain.model.UpdateBook

interface BookRepository {

    /**
     * 書籍をIDで取得する
     * @param id 書籍のID
     * @return Book 検索結果の書籍
     */
    fun findById(id: BookId): Book?

    /**
     * 書籍を検索する
     * @param title 書籍のタイトル
     * @param authorId 書籍の著者ID
     * @return List<Book> 検索結果の書籍リスト
     */
    fun findByIndexOrAll(title: BookTitle?, authorId: AuthorId?): List<Book>

    /**
     * 書籍を新規登録する
     * @param model 登録する書籍の属性情報
     * @return Long 登録した書籍のID
     */
    fun insert(newBook: NewBook): BookId

    /**
     * 書籍の属性情報を更新する
     * @param updateBook 更新する書籍の属性情報
     * @return Int 更新した書籍の件数
     * @throws IllegalArgumentException If the ISBN already exists.
     * @throws IllegalArgumentException If the author does not exist.
     * @throws IllegalStateException If the update count is unexpected.
     * @throws IllegalArgumentException If the book does not exist.
     */
    fun update(updateBook: UpdateBook): Int

    /**
     * 書籍を削除する
     * @param id 削除する書籍のID
     * @throws IllegalArgumentException If the book does not exist.
     * @return Int 削除した書籍の件数
     * */
    fun delete(deleteBook: Book): Int
}
