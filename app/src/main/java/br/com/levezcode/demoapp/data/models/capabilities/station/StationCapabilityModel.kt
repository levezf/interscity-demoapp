package br.com.levezcode.demoapp.data.models.capabilities.station

import br.com.levezcode.demoapp.commom.capability.ICapability
import br.com.levezcode.demoapp.data.models.ResourceSensorDataModel
import com.google.gson.annotations.SerializedName

data class StationCapabilityModel(
    @SerializedName("Higrômetro")
    val hygrometerSensor: List<ResourceSensorDataModel> = emptyList(),

    @SerializedName("Anemômetro")
    val anemometerSensor: List<ResourceSensorDataModel> = emptyList(),

    @SerializedName("Luminosidade")
    val luminositySensor: List<ResourceSensorDataModel> = emptyList(),

    @SerializedName("Termômetro")
    val thermometerSensor: List<ResourceSensorDataModel> = emptyList(),

    @SerializedName("Barômetro")
    val barometerSensor: List<ResourceSensorDataModel> = emptyList(),

    @SerializedName("Altímetro")
    val altimeterSensor: List<ResourceSensorDataModel> = emptyList(),
) : ICapability
