package br.com.levezcode.demoapp.data.extensions

import br.com.levezcode.demoapp.data.models.ResourceSensorDataModel
import br.com.levezcode.demoapp.domain.entities.ResourceData

fun List<ResourceSensorDataModel>.getFirstOrDefault(
    default: ResourceSensorDataModel = ResourceSensorDataModel()
) = if (this.isNotEmpty()) {
    this[0]
} else {
    default
}

fun ResourceSensorDataModel.toEntity(): ResourceData = ResourceData(
    value = this.value,
    date = this.date
)
