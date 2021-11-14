package br.com.levezcode.demoapp.presentation.maps.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.levezcode.demoapp.R
import br.com.levezcode.demoapp.databinding.MapsFragmentBinding
import br.com.levezcode.demoapp.domain.entities.ResourceDetails
import br.com.levezcode.demoapp.presentation.maps.adapter.MarkerInfoWindowAdapter
import br.com.levezcode.demoapp.presentation.maps.viewmodel.MapsViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : Fragment() {

    companion object {
        fun newInstance() = MapsFragment()
    }

    private lateinit var binding: MapsFragmentBinding

    private val viewModel: MapsViewModel by viewModel()

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-22.0104, -47.5327)))
        googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(requireContext()))
        googleMap.uiSettings.isMapToolbarEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true

        viewModel.resources.observe(
            viewLifecycleOwner,
            { resources -> buildMarkers(googleMap, resources) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MapsFragmentBinding.inflate(inflater, container, false).let {
        binding = it
        it.lifecycleOwner = viewLifecycleOwner
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(
            callback
        )
    }

    private fun buildMarkers(googleMap: GoogleMap, resources: List<ResourceDetails>?) {
        googleMap.clear()

        resources?.forEach { resource ->
            run {
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .title(resource.description)
                        .position(LatLng(resource.lat ?: 0.0, resource.lon ?: 0.0))
                )
                marker?.tag = resource
            }
        }
    }
}
