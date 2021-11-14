package br.com.levezcode.demoapp.presentation.maps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import br.com.levezcode.demoapp.databinding.MarkerInfoContentsBinding
import br.com.levezcode.demoapp.domain.entities.ResourceDetails
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? = null

    override fun getInfoContents(marker: Marker): View? =
        (marker.tag as? ResourceDetails)?.run {
            MarkerInfoContentsBinding.inflate(LayoutInflater.from(context)).also {
                it.resource = this
                it.executePendingBindings()
            }.root
        }
}
