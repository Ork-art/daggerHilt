package com.example.domain.base

import kotlinx.coroutines.flow.Flow

interface BaseFlowUseCase<T, R>{
    fun invoke(input:Unit): Flow<R>
}