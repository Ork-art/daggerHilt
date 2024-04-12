package com.example.domain.usecase

interface BaseUseCase<T, R> {
   suspend fun invoke(input:T):R
}