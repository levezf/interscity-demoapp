package br.com.levezcode.demoapp.data.datasources.remote

import br.com.levezcode.demoapp.data.models.CollectorModel
import br.com.levezcode.demoapp.data.models.WrapperResourceModel
import br.com.levezcode.demoapp.data.models.capabilities.standalone.AnemometerCapabilityModel
import br.com.levezcode.demoapp.data.models.capabilities.standalone.ThermometerCapabilityModel
import br.com.levezcode.demoapp.data.models.capabilities.station.StationCapabilityModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IRemoteDataSource {
    suspend fun findStationResourceDataByUUID(
        uuid: String
    ): CollectorModel<StationCapabilityModel>?

    suspend fun findAnemometroResourceDataByUUID(
        uuid: String
    ): CollectorModel<AnemometerCapabilityModel>?

    suspend fun findTermometroResourceDataByUUID(
        uuid: String
    ): CollectorModel<ThermometerCapabilityModel>?

    suspend fun findResources(): WrapperResourceModel?

    suspend fun sendStationData(uuid: String, stationData: Map<String, Any>)

    suspend fun sendThermometerData(uuid: String, thermometerData: Map<String, Any>)

    suspend fun sendAnemometerData(uuid: String, anemometerData: Map<String, Any>)
}

@JvmSuppressWildcards
interface InterscityRemoteDataSource : IRemoteDataSource {

    @GET("/collector/resources/{uuid}/data/last")
    override suspend fun findStationResourceDataByUUID(
        @Path("uuid") uuid: String
    ): CollectorModel<StationCapabilityModel>?

    @GET("/collector/resources/{uuid}/data/last")
    override suspend fun findAnemometroResourceDataByUUID(
        @Path("uuid") uuid: String
    ): CollectorModel<AnemometerCapabilityModel>?

    @GET("/collector/resources/{uuid}/data/last")
    override suspend fun findTermometroResourceDataByUUID(
        @Path("uuid") uuid: String
    ): CollectorModel<ThermometerCapabilityModel>?

    @GET("/catalog/resources")
    override suspend fun findResources(): WrapperResourceModel?

    @POST("/adaptor/resources/{uuid}/data")
    override suspend fun sendStationData(
        @Path("uuid") uuid: String,
        @Body stationData: Map<String, Any>
    )

    @POST("/adaptor/resources/{uuid}/data")
    override suspend fun sendThermometerData(
        @Path("uuid") uuid: String,
        @Body thermometerData: Map<String, Any>
    )

    @POST("/adaptor/resources/{uuid}/data")
    override suspend fun sendAnemometerData(
        @Path("uuid") uuid: String,
        @Body anemometerData: Map<String, Any>
    )
}
