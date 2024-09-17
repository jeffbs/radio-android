package org.jibs.radio.android.presentation.station.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jibs.radio.android.databinding.FragmentStationDetailBinding

@AndroidEntryPoint
class StationDetailFragment : Fragment() {

    private val viewModel: StationDetailViewModel by viewModels()
    private lateinit var binding: FragmentStationDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stationId = StationDetailFragmentArgs.fromBundle(requireArguments()).stationId

        lifecycleScope.launch {
            viewModel.station.collectLatest {
                it?.apply {
                    binding.name.text = it.name
                    binding.city.text = it.city
                    binding.country.text = it.country
                    Glide.with(binding.stationImage.context)
                        .load(logoLarge)
                        //.placeholder(R.drawable.placeholder_image)
                        //.error(R.drawable.error_image)
                        .into(binding.stationImage)
                }
            }
        }

        viewModel.requestStation(stationId)
    }
}
