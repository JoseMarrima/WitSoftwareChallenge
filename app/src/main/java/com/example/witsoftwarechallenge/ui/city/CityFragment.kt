package com.example.witsoftwarechallenge.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.witsoftwarechallenge.R
import com.example.witsoftwarechallenge.WeatherApplication
import com.example.witsoftwarechallenge.databinding.CityFragmentBinding
import com.example.witsoftwarechallenge.util.Resource
import com.google.android.material.snackbar.Snackbar

class CityFragment : Fragment() {

    private val viewModel: CityViewModel by viewModels {
        CityViewModelFactory((requireContext().applicationContext as WeatherApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: CityFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.city_fragment, container, false)

        binding.lifecycleOwner = this

        val args = CityFragmentArgs.fromBundle(requireArguments())

        viewModel.setCityName(args.city)

        fun loading(boolean: Boolean) {
            if (boolean) {
                binding.loading.visibility = View.VISIBLE
                binding.layoutCityFragment.visibility = View.GONE
            } else {
                binding.loading.visibility = View.GONE
                binding.layoutCityFragment.visibility = View.VISIBLE
            }
        }

        viewModel.currentWeather.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    loading(false)
                    binding.weather = it.data
                }
                Resource.Status.LOADING -> {
                    loading(true)
                }
                Resource.Status.ERROR -> {
                    loading(false)
                    showError(it.message!!)
                }
            }
        })


        return binding.root
    }

    private fun showError(msg: String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.dismiss)) {}.show()
    }
}