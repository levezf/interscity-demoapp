package br.com.levezcode.demoapp.presentation.sensors.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.levezcode.demoapp.databinding.SensorFragmentBinding
import br.com.levezcode.demoapp.presentation.sensors.viewmodel.SensorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SensorFragment : Fragment() {

    companion object {
        fun newInstance() = SensorFragment()
    }

    private val sensorViewModel: SensorViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = SensorFragmentBinding.inflate(inflater, container, false).let {
        it.viewModel = sensorViewModel
        it.lifecycleOwner = this@SensorFragment
        it.root
    }
}
