package br.com.levezcode.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class CollectorModel<Capabilities>(
    @SerializedName("resources") val resourceModels: List<ResourceModel<Capabilities>>
)
