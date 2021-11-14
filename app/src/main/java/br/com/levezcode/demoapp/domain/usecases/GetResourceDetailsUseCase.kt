package br.com.levezcode.demoapp.domain.usecases

import br.com.levezcode.demoapp.commom.usecase.NoParams
import br.com.levezcode.demoapp.commom.usecase.UseCase
import br.com.levezcode.demoapp.domain.entities.ResourceDetails
import br.com.levezcode.demoapp.domain.repositories.IResourceRepository
import kotlinx.coroutines.flow.Flow

class GetResourceDetailsUseCase(
    private val repository: IResourceRepository
) : UseCase<NoParams, List<ResourceDetails>> {

    override suspend fun invoke(param: NoParams): Flow<List<ResourceDetails>> =
        repository.findResources()
}
