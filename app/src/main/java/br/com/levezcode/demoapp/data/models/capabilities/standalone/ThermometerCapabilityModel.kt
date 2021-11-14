package br.com.levezcode.demoapp.data.models.capabilities.standalone

import br.com.levezcode.demoapp.commom.capability.ICapability
import br.com.levezcode.demoapp.data.models.ResourceSensorDataModel
import com.google.gson.annotations.SerializedName

data class ThermometerCapabilityModel(
    @SerializedName("Termometro") val thermometerSensor: List<ResourceSensorDataModel> = emptyList()
) : ICapability
