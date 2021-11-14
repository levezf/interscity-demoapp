package br.com.levezcode.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class ResourceModel<Capabilities>(
    @SerializedName("uuid") val uuid: String = "",
    @SerializedName("capabilities") val capabilities: Capabilities? = null
)
