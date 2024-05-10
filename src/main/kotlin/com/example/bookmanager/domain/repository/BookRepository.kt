package com.example.bookmanager.domain.repository

import com.example.bookmanager.domain.model.*
import java.time.LocalDate

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
     * @param id 更新する書籍のID
     * @param title 書籍のタイトル
     * @param isbn 書籍のISBN
     * @param publishedDate 書籍の出版日
     * @param authorId 書籍の著者ID
     * @throws IllegalArgumentException If the book does not exist.
     */
    fun updateById(id: BookId, title: BookTitle?, isbn: ISBN?, publishedDate: LocalDate?, authorId: AuthorId?): Int

    /**
     * 書籍を削除する
     * @param id 削除する書籍のID
     * @throws IllegalArgumentException If the book does not exist.
     * @return Int 削除した書籍の件数
     * */
    fun deleteById(id: BookId): Int
}