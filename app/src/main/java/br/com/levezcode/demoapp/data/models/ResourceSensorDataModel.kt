package br.com.levezcode.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class ResourceSensorDataModel(
    @SerializedName("value") val value: Double = 0.0,
    @SerializedName("date") val date: String = ""
)
