package org.jibs.radio.android.presentation.station.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jibs.radio.android.databinding.FragmentStationListBinding
import timber.log.Timber


@AndroidEntryPoint
class StationListFragment : Fragment() {

    private val viewModel: StationListViewModel by viewModels()
    private lateinit var binding: FragmentStationListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        binding = FragmentStationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StationListAdapter { station ->
            findNavController().navigate(
                StationListFragmentDirections.actionStationListFragmentToStationDetailFragment(station.id)
            )
        }

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch (Dispatchers.Main) {
            viewModel.stations.collect {
                adapter.submitList(it)
            }
        }

        viewModel.requestStations()
    }
}