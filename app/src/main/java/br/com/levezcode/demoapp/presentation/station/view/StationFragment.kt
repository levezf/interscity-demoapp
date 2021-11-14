package br.com.levezcode.demoapp.presentation.station.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.levezcode.demoapp.databinding.StationFragmentBinding
import br.com.levezcode.demoapp.presentation.station.viewmodel.StationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StationFragment : Fragment() {

    companion object {
        fun newInstance() = StationFragment()
    }

    private val stationViewModel: StationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = StationFragmentBinding.inflate(inflater, container, false).let {
        it.viewModel = stationViewModel
        it.lifecycleOwner = this@StationFragment
        it.root
    }
}
