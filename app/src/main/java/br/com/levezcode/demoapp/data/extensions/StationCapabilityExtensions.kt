package br.com.levezcode.demoapp.data.extensions

import br.com.levezcode.demoapp.data.models.capabilities.station.StationCapabilityModel
import br.com.levezcode.demoapp.domain.entities.StationCapability

fun StationCapabilityModel.toEntity(): StationCapability = StationCapability(
    hygrometerSensor = this.hygrometerSensor.getFirstOrDefault().toEntity(),
    anemometerSensor = this.anemometerSensor.getFirstOrDefault().toEntity(),
    luminositySensor = this.luminositySensor.getFirstOrDefault().toEntity(),
    thermometerSensor = this.thermometerSensor.getFirstOrDefault().toEntity(),
    barometerSensor = this.barometerSensor.getFirstOrDefault().toEntity(),
    altimeterSensor = this.altimeterSensor.getFirstOrDefault().toEntity(),
)
