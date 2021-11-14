package br.com.levezcode.demoapp.domain.usecases

import br.com.levezcode.demoapp.commom.usecase.NoParams
import br.com.levezcode.demoapp.commom.usecase.UseCase
import br.com.levezcode.demoapp.domain.entities.StationCapability
import br.com.levezcode.demoapp.domain.repositories.IResourceRepository
import kotlinx.coroutines.flow.Flow

class GetStationUseCase(
    private val repository: IResourceRepository
) : UseCase<NoParams, StationCapability> {

    override suspend fun invoke(param: NoParams): Flow<StationCapability> =
        repository.findStationData()
}
