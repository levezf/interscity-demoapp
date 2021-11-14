package br.com.levezcode.demoapp.domain.usecases

import br.com.levezcode.demoapp.commom.usecase.NoParams
import br.com.levezcode.demoapp.commom.usecase.UseCase
import br.com.levezcode.demoapp.domain.entities.SensorSingleCapability
import br.com.levezcode.demoapp.domain.repositories.IResourceRepository
import kotlinx.coroutines.flow.Flow

class GetThermometerUseCase(
    private val repository: IResourceRepository
) : UseCase<NoParams, SensorSingleCapability> {

    override suspend fun invoke(param: NoParams): Flow<SensorSingleCapability> =
        repository.findThermometerData()
}
