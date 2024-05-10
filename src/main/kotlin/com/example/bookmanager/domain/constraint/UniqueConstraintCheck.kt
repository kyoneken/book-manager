package com.example.bookmanager.domain.constraint

interface UniqueConstraintCheck<T> {
    fun exists(column: T): Boolean
}
