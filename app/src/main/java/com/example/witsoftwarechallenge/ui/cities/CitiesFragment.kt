package com.example.witsoftwarechallenge.ui.cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.witsoftwarechallenge.R

import com.example.witsoftwarechallenge.databinding.CitiesFragmentBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class CitiesFragment : Fragment() {

    private val viewModel: CitiesViewModel by viewModels()

    private val adapter = CitiesAdapter(ClickListener {
        this.findNavController().navigate(CitiesFragmentDirections
            .actionCitiesFragmentToCityFragment(it))
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        val binding: CitiesFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.cities_fragment, container, false)

        binding.lifecycleOwner = this

        binding.listComicsRv.adapter = adapter

        adapter.submitList(viewModel.cities)

        return binding.root
    }
}