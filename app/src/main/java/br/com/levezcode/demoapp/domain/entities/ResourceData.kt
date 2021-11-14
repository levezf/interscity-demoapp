package br.com.levezcode.demoapp.domain.entities

import java.util.Calendar
import java.util.Locale

const val TEMPLATE_LAST_UPDATE = "%02d/%02d/%4d %02d:%02d"
const val NO_UPDATES_YET = "Sem atualizações até o momento"

data class ResourceData(
    val value: Double = 0.0,
    val date: String = "",
    val lastUpdate: String = getLastUpdate(value > 0.0)
)

fun getLastUpdate(containsValue: Boolean): String =
    if (containsValue) {
        Calendar.getInstance(Locale.getDefault()).let {
            TEMPLATE_LAST_UPDATE.format(
                it.get(Calendar.DAY_OF_MONTH),
                it.get(Calendar.MONTH) + 1,
                it.get(Calendar.YEAR),
                it.get(Calendar.HOUR_OF_DAY),
                it.get(Calendar.MINUTE)
            )
        }
    } else {
        NO_UPDATES_YET
    }
