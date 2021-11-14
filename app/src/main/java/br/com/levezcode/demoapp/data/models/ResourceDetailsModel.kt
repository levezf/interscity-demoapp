package br.com.levezcode.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class ResourceDetailsModel(
    @SerializedName("id") val id: Long? = 0,
    @SerializedName("lat") val lat: Double? = 0.0,
    @SerializedName("lon") val lon: Double? = 0.0,
    @SerializedName("status") val status: String? = "active",
    @SerializedName("description") val description: String? = null,
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("capabilities") val capabilities: List<String>? = emptyList()
)
