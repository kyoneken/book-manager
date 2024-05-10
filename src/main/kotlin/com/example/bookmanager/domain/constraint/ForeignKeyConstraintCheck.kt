package com.example.bookmanager.domain.constraint

interface ForeignKeyConstraintCheck<T> {
    fun exists(key: T): Boolean
}
