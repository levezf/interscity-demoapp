package br.com.levezcode.demoapp.domain.entities

import br.com.levezcode.demoapp.commom.capability.ICapability

data class SensorSingleCapability(
    val single: ResourceData = ResourceData()
) : ICapability
