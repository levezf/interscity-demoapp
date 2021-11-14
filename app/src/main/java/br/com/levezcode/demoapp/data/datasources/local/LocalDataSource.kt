package br.com.levezcode.demoapp.data.datasources.local

import android.content.SharedPreferences
import br.com.levezcode.demoapp.data.models.ResourceDetailsModel
import br.com.levezcode.demoapp.data.models.capabilities.standalone.AnemometerCapabilityModel
import br.com.levezcode.demoapp.data.models.capabilities.standalone.ThermometerCapabilityModel
import br.com.levezcode.demoapp.data.models.capabilities.station.StationCapabilityModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder

interface ILocalDataSource {
    fun findLastStationData(): StationCapabilityModel

    fun setLastStationData(stationCapabilityModel: StationCapabilityModel)

    fun setLastAnemometroData(anemometerCapabilityModel: AnemometerCapabilityModel)

    fun findLastAnemometroData(): AnemometerCapabilityModel

    fun setLastTemometroData(thermometerCapabilityModel: ThermometerCapabilityModel)

    fun findLastTermometroData(): ThermometerCapabilityModel

    fun setLastResourcesDetails(resources: List<ResourceDetailsModel>)

    fun findLastResourceDetails(): List<ResourceDetailsModel>
}

class SharedPreferencesDataSource(
    private val sharedPreferences: SharedPreferences
) : ILocalDataSource {
    private val gson: Gson = GsonBuilder().create()

    companion object {
        private const val KEY_LAST_STATION = "last_station"
        private const val KEY_LAST_ANEMOMETRO = "last_anemometro"
        private const val KEY_LAST_TERMOMETRO = "last_termometro"
        private const val KEY_LAST_DETAILS = "last_details"
    }

    override fun setLastResourcesDetails(resources: List<ResourceDetailsModel>) =
        save(KEY_LAST_DETAILS, resources)

    override fun setLastStationData(stationCapabilityModel: StationCapabilityModel) =
        save(KEY_LAST_STATION, stationCapabilityModel)

    override fun setLastAnemometroData(anemometerCapabilityModel: AnemometerCapabilityModel) =
        save(KEY_LAST_ANEMOMETRO, anemometerCapabilityModel)

    override fun setLastTemometroData(thermometerCapabilityModel: ThermometerCapabilityModel) =
        save(KEY_LAST_TERMOMETRO, thermometerCapabilityModel)

    override fun findLastStationData(): StationCapabilityModel =
        get(KEY_LAST_STATION, StationCapabilityModel())

    override fun findLastAnemometroData(): AnemometerCapabilityModel =
        get(KEY_LAST_ANEMOMETRO, AnemometerCapabilityModel())

    override fun findLastTermometroData(): ThermometerCapabilityModel =
        get(KEY_LAST_TERMOMETRO, ThermometerCapabilityModel())

    override fun findLastResourceDetails(): List<ResourceDetailsModel> =
        get(KEY_LAST_DETAILS, emptyList())

    private fun save(key: String, value: Any) =
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply()

    private inline fun <reified T> get(key: String, default: T): T {
        val json = sharedPreferences.getString(key, null)
        return try {
            json?.run {
                gson.fromJson(json, T::class.java)
            } ?: default
        } catch (e: Exception) {
            default
        }
    }
}
