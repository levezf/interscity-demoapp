package br.com.levezcode.demoapp.domain.entities

import br.com.levezcode.demoapp.commom.capability.ICapability

data class StationCapability(
    val hygrometerSensor: ResourceData = ResourceData(),
    val anemometerSensor: ResourceData = ResourceData(),
    val luminositySensor: ResourceData = ResourceData(),
    val thermometerSensor: ResourceData = ResourceData(),
    val barometerSensor: ResourceData = ResourceData(),
    val altimeterSensor: ResourceData = ResourceData(),
) : ICapability
