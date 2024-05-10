/*
 * This file is generated by jOOQ.
 */
package com.example.bookmanager.generated.jooq.tables.records;


import com.example.bookmanager.generated.jooq.tables.Book;

import java.time.LocalDate;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BookRecord extends UpdatableRecordImpl<BookRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.book.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.book.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.book.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.book.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.book.isbn</code>.
     */
    public void setIsbn(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.book.isbn</code>.
     */
    public String getIsbn() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.book.published_date</code>.
     */
    public void setPublishedDate(LocalDate value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.book.published_date</code>.
     */
    public LocalDate getPublishedDate() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>public.book.author_id</code>.
     */
    public void setAuthorId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.book.author_id</code>.
     */
    public Long getAuthorId() {
        return (Long) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BookRecord
     */
    public BookRecord() {
        super(Book.BOOK);
    }

    /**
     * Create a detached, initialised BookRecord
     */
    public BookRecord(Long id, String title, String isbn, LocalDate publishedDate, Long authorId) {
        super(Book.BOOK);

        setId(id);
        setTitle(title);
        setIsbn(isbn);
        setPublishedDate(publishedDate);
        setAuthorId(authorId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised BookRecord
     */
    public BookRecord(com.example.bookmanager.generated.jooq.tables.pojos.Book value) {
        super(Book.BOOK);

        if (value != null) {
            setId(value.getId());
            setTitle(value.getTitle());
            setIsbn(value.getIsbn());
            setPublishedDate(value.getPublishedDate());
            setAuthorId(value.getAuthorId());
            resetChangedOnNotNull();
        }
    }
}