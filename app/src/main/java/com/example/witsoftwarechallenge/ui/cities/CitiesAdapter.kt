package com.example.witsoftwarechallenge.ui.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.witsoftwarechallenge.databinding.CitiesItemLayoutBinding

class CitiesAdapter(private val clickListener: ClickListener) :
    ListAdapter<String, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CitiesViewHolder(CitiesItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CitiesViewHolder).bind(clickListener, getItem(position))
    }

    class CitiesViewHolder(private val binding: CitiesItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ClickListener, item: String) {
            binding.apply {
                clickListener = listener
                city = item
                executePendingBindings()
            }
        }
    }
}


class DiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class ClickListener(val clickListener: (cityName: String) -> Unit) {
    fun onClick(cityName: String) = clickListener(cityName)
}
