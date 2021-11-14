package br.com.levezcode.demoapp.data.models.capabilities.standalone

import br.com.levezcode.demoapp.commom.capability.ICapability
import br.com.levezcode.demoapp.data.models.ResourceSensorDataModel
import com.google.gson.annotations.SerializedName

data class AnemometerCapabilityModel(
    @SerializedName("Anemometro") val anemometerSensor: List<ResourceSensorDataModel> = emptyList()
) : ICapability
