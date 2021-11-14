package br.com.levezcode.demoapp.commom.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<Param, Result> {
    suspend operator fun invoke(param: Param): Flow<Result>
}
