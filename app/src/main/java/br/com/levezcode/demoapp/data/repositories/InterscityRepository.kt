package br.com.levezcode.demoapp.data.repositories

import android.util.Log
import br.com.levezcode.demoapp.data.datasources.local.ILocalDataSource
import br.com.levezcode.demoapp.data.datasources.remote.IRemoteDataSource
import br.com.levezcode.demoapp.data.extensions.getFirstOrDefault
import br.com.levezcode.demoapp.data.extensions.toEntity
import br.com.levezcode.demoapp.data.extensions.toListOfEntity
import br.com.levezcode.demoapp.data.models.ResourceDetailsModel
import br.com.levezcode.demoapp.data.models.capabilities.standalone.AnemometerCapabilityModel
import br.com.levezcode.demoapp.data.models.capabilities.standalone.ThermometerCapabilityModel
import br.com.levezcode.demoapp.data.models.capabilities.station.StationCapabilityModel
import br.com.levezcode.demoapp.domain.entities.ResourceDetails
import br.com.levezcode.demoapp.domain.entities.SensorSingleCapability
import br.com.levezcode.demoapp.domain.entities.StationCapability
import br.com.levezcode.demoapp.domain.repositories.IResourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InterscityRepository(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : IResourceRepository {

    companion object {
        private const val UUID_ANEMOMETRO = "ee771f3f-5959-41d1-91f6-f14097a30782"
        private const val UUID_TERMOMETRO = "5f93a78d-dace-4dc8-bb55-28d0f5aa5716"
        private const val UUID_STATION_SENSORS = "45cc994c-48be-407c-bc20-26a6cc221cc8"
    }

    override suspend fun findStationData(): Flow<StationCapability> = flow {
        var stationCapability: StationCapabilityModel? = null

        try {
            remoteDataSource.findStationResourceDataByUUID(UUID_STATION_SENSORS).let {
                it?.run {
                    stationCapability = this.resourceModels.getFirstOrDefault().capabilities
                }
            }
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Fail to find station data", e)
        }

        stationCapability?.apply {
            emit(this.toEntity())
            localDataSource.setLastStationData(this)
        } ?: emit(localDataSource.findLastStationData().toEntity())
    }.flowOn(Dispatchers.IO)

    override suspend fun findAnemometerData(): Flow<SensorSingleCapability> = flow {
        var capability: AnemometerCapabilityModel? = null

        try {
            remoteDataSource.findAnemometroResourceDataByUUID(UUID_ANEMOMETRO).let {
                it?.run {
                    capability = this.resourceModels.getFirstOrDefault().capabilities
                }
            }
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Fail to find anemometer data", e)
        }

        capability?.apply {
            emit(this.toEntity())
            localDataSource.setLastAnemometroData(this)
        } ?: emit(localDataSource.findLastAnemometroData().toEntity())
    }.flowOn(Dispatchers.IO)

    override suspend fun findThermometerData(): Flow<SensorSingleCapability> = flow {
        var capability: ThermometerCapabilityModel? = null

        try {
            remoteDataSource.findTermometroResourceDataByUUID(UUID_TERMOMETRO).let {
                it?.run {
                    capability = this.resourceModels.getFirstOrDefault().capabilities
                }
            }
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Fail to find thermometer data", e)
        }


        capability?.apply {
            emit(this.toEntity())
            localDataSource.setLastTemometroData(this)
        } ?: emit(localDataSource.findLastTermometroData().toEntity())
    }.flowOn(Dispatchers.IO)

    override suspend fun findResources(): Flow<List<ResourceDetails>> = flow {
        var resourceDetails: List<ResourceDetailsModel>? = null

        try {
            remoteDataSource.findResources().let {
                it?.run {
                    resourceDetails = it.resourcesModel
                }
            }
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Fail to find resources data", e)
        }

        resourceDetails?.apply {
            emit(this.toListOfEntity())
            localDataSource.setLastResourcesDetails(this)
        } ?: emit(localDataSource.findLastResourceDetails().toListOfEntity())
    }.flowOn(Dispatchers.IO)

    override suspend fun sendStationData(stationData: Map<String, Any>) {
        try {
            remoteDataSource.sendStationData(UUID_STATION_SENSORS, stationData)
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Fail to send station simulated data", e)
        }
    }

    override suspend fun sendThermometerData(thermometerData: Map<String, Any>) {
        try {
            remoteDataSource.sendThermometerData(UUID_TERMOMETRO, thermometerData)
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Fail to send thermometer simulated data", e)
        }
    }

    override suspend fun sendAnemometerData(anemometerData: Map<String, Any>) {
        try {
            remoteDataSource.sendAnemometerData(UUID_ANEMOMETRO, anemometerData)
        } catch (e: Exception) {
            Log.d(this::class.simpleName, "Fail to send anemometer simulated data", e)
        }
    }
}
