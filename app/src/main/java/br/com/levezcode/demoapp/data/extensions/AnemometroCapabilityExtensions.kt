package br.com.levezcode.demoapp.data.extensions

import br.com.levezcode.demoapp.data.models.capabilities.standalone.AnemometerCapabilityModel
import br.com.levezcode.demoapp.domain.entities.SensorSingleCapability

fun AnemometerCapabilityModel.toEntity(): SensorSingleCapability = SensorSingleCapability(
    single = this.anemometerSensor.getFirstOrDefault().toEntity(),
)
