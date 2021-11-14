package br.com.levezcode.demoapp.domain.entities

data class ResourceDetails(
    val id: Long? = 0,
    val lat: Double? = 0.0,
    val lon: Double? = 0.0,
    val status: String? = "active",
    val description: String? = null,
    val uuid: String? = null,
    val capabilities: List<String>? = emptyList()
)
