package br.com.levezcode.demoapp.data.extensions

import br.com.levezcode.demoapp.commom.capability.ICapability
import br.com.levezcode.demoapp.data.models.ResourceModel

fun <Capability : ICapability> List<ResourceModel<Capability>>.getFirstOrDefault(
    default: ResourceModel<Capability> = ResourceModel()
) = if (this.isNotEmpty()) {
    this[0]
} else {
    default
}
