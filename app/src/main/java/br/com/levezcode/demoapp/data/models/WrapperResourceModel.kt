package br.com.levezcode.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class WrapperResourceModel(
    @SerializedName("resources") val resourcesModel: List<ResourceDetailsModel>
)
