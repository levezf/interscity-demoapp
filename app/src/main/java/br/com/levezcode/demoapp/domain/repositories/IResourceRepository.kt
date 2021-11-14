package br.com.levezcode.demoapp.domain.repositories

import br.com.levezcode.demoapp.domain.entities.ResourceDetails
import br.com.levezcode.demoapp.domain.entities.SensorSingleCapability
import br.com.levezcode.demoapp.domain.entities.StationCapability
import kotlinx.coroutines.flow.Flow

interface IResourceRepository {

    suspend fun findStationData(): Flow<StationCapability>

    suspend fun findAnemometerData(): Flow<SensorSingleCapability>

    suspend fun findThermometerData(): Flow<SensorSingleCapability>

    suspend fun findResources(): Flow<List<ResourceDetails>>

    suspend fun sendStationData(stationData: Map<String, Any>)

    suspend fun sendThermometerData(thermometerData: Map<String, Any>)

    suspend fun sendAnemometerData(anemometerData: Map<String, Any>)
}
