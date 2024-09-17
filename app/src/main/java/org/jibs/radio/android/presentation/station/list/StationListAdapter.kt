package org.jibs.radio.android.presentation.station.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.jibs.radio.android.databinding.ListItemStationBinding
import org.jibs.radio.android.domain.model.Station
import timber.log.Timber

class StationListAdapter(
    private val onClick: (Station) -> Unit
) : ListAdapter<Station, StationListAdapter.StationViewHolder>(StationDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        Timber.d("onCreateViewHolder")
        val binding = ListItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        Timber.d("onBindViewHolder $position")
        val station = getItem(position)
        holder.bind(station)
        holder.itemView.setOnClickListener { onClick(station) }
    }

    class StationViewHolder(private val binding: ListItemStationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(station: Station) {
            binding.name.text = station.name
            Glide.with(binding.imageView.context)
                .load(station.logo)
                //.placeholder(R.drawable.placeholder_image)
                //.error(R.drawable.error_image)
                .into(binding.imageView)
        }
    }
}

object StationDiffCallback : DiffUtil.ItemCallback<Station>() {
    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.id == newItem.id
    }
}