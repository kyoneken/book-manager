package com.example.bookmanager.infrastructure.repository

import com.example.bookmanager.domain.model.Author
import com.example.bookmanager.domain.model.AuthorId
import com.example.bookmanager.domain.model.AuthorName
import com.example.bookmanager.domain.model.Email
import com.example.bookmanager.domain.repository.AuthorRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import com.example.bookmanager.generated.jooq.tables.Author as JooqTablesAuthor

@Repository
class AuthorRepositoryImpl(
    private val dslContext: DSLContext
) : AuthorRepository {
    override fun findById(id: AuthorId): Author? {
        val author = JooqTablesAuthor.AUTHOR
        val record = dslContext.select()
            .from(author)
            .where(author.ID.eq(id.value))
            .fetchOne()
        return record?.map {
            Author(
                AuthorId.create(it[author.ID]),
                AuthorName.create(it[author.NAME]),
                Email.create(it[author.EMAIL])
            )
        }
    }

    override fun selectAll(): List<Author> {
        val author = JooqTablesAuthor.AUTHOR
        val records = dslContext.select()
            .from(author)
            .orderBy(author.ID.asc())
            .fetch()
        return records.map {
            Author(
                AuthorId.create(it[author.ID]),
                AuthorName.create(it[author.NAME]),
                Email.create(it[author.EMAIL])
            )
        }
    }
}
