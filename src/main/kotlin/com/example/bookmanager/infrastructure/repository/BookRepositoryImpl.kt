package com.example.bookmanager.infrastructure.repository

import com.example.bookmanager.domain.model.*
import com.example.bookmanager.domain.repository.BookRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDate
import com.example.bookmanager.generated.jooq.tables.Author as JooqTablesAuthor
import com.example.bookmanager.generated.jooq.tables.Book as JooqTablesBook

@Repository
class BookRepositoryImpl(
    private val dslContext: DSLContext
): BookRepository {

    override fun findById(id: BookId): Book? {
        val author = JooqTablesAuthor.AUTHOR
        val book = JooqTablesBook.BOOK
        return dslContext.select()
            .from(book)
            .innerJoin(author).on(book.AUTHOR_ID.eq(author.ID))
            .where(book.ID.eq(id.value))
            .fetchOne()
            ?.let {
                Book(
                    BookId.create(it[book.ID]),
                    BookTitle.create(it[book.TITLE]),
                    ISBN.create(it[book.ISBN]),
                    it[book.PUBLISHED_DATE],
                    Author(
                        AuthorId.create(it[author.ID]),
                        AuthorName.create(it[author.NAME]),
                        Email.create(it[author.EMAIL])
                    )
                )
            }
    }
    override fun findByIndexOrAll(title: BookTitle?, authorId: AuthorId?): List<Book> {
        val author = JooqTablesAuthor.AUTHOR
        val book = JooqTablesBook.BOOK
        val selectQuery = dslContext.select()
            .from(book)
            .innerJoin(author).on(book.AUTHOR_ID.eq(author.ID))
        title?.let {
            selectQuery.where(book.TITLE.like("${title.value}%"))
        }
        authorId?.let {
            selectQuery.where(book.AUTHOR_ID.eq(authorId.value))
        }
        selectQuery.orderBy(book.ID.asc())
        return selectQuery.fetch()
            .map {
                Book(
                    BookId.create(it[book.ID]),
                    BookTitle.create(it[book.TITLE]),
                    ISBN.create(it[book.ISBN]),
                    it[book.PUBLISHED_DATE],
                    Author(
                        AuthorId.create(it[author.ID]),
                        AuthorName.create(it[author.NAME]),
                        Email.create(it[author.EMAIL])
                    )
                )
            }
    }

    override fun insert(newBook: NewBook): BookId {
        val book = JooqTablesBook.BOOK
        return dslContext.insertInto(book)
            .set(book.TITLE, newBook.title.value)
            .set(book.ISBN, newBook.isbn.value)
            .set(book.PUBLISHED_DATE, newBook.publishedDate)
            .set(book.AUTHOR_ID, newBook.authorId.value)
            .returning(book.ID)
            .fetchOne()
            ?.map {
                BookId.create(it[book.ID])
            } ?: throw IllegalArgumentException("書籍の登録に失敗しました")
    }

    override fun updateById(id: BookId, title: BookTitle?, isbn: ISBN?, publishedDate: LocalDate?, authorId: AuthorId?): Int {
        val book = JooqTablesBook.BOOK
        val updateQuery = dslContext.update(book)
            .set(book.ID, id.value)
        title?.let {
            updateQuery.set(book.TITLE, title.value)
        }
        isbn?.let {
            updateQuery.set(book.ISBN, isbn.value)
        }
        publishedDate?.let {
            updateQuery.set(book.PUBLISHED_DATE, publishedDate)
        }
        authorId?.let {
            updateQuery.set(book.AUTHOR_ID, authorId.value)
        }
        updateQuery.where(book.ID.eq(id.value))
        return updateQuery.execute()
    }

    override fun deleteById(id: BookId): Int {
        val book = JooqTablesBook.BOOK
        return dslContext.deleteFrom(book)
            .where(book.ID.eq(id.value))
            .execute()
    }
}