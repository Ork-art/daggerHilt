package com.example.domain.validator

interface BaseValidator<T> {
    fun isValid(input:T):Boolean
}