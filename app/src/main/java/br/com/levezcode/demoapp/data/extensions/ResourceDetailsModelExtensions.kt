package br.com.levezcode.demoapp.data.extensions

import br.com.levezcode.demoapp.data.models.ResourceDetailsModel
import br.com.levezcode.demoapp.domain.entities.ResourceDetails
import java.lang.Exception

fun List<ResourceDetailsModel>.toListOfEntity(): List<ResourceDetails> =
    try {
        if (this.isNotEmpty()) {
            this.map { it.toEntity() }.toList()
        } else {
            emptyList()
        }
    } catch (e: Exception) {
        emptyList()
    }

fun ResourceDetailsModel.toEntity(): ResourceDetails = ResourceDetails(
    id = this.id,
    lat = this.lat,
    lon = this.lon,
    status = this.status,
    description = this.description,
    uuid = this.uuid,
    capabilities = this.capabilities
)
