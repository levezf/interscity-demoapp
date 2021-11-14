package br.com.levezcode.demoapp.data.extensions

import br.com.levezcode.demoapp.data.models.capabilities.standalone.ThermometerCapabilityModel
import br.com.levezcode.demoapp.domain.entities.SensorSingleCapability

fun ThermometerCapabilityModel.toEntity(): SensorSingleCapability = SensorSingleCapability(
    single = this.thermometerSensor.getFirstOrDefault().toEntity(),
)
