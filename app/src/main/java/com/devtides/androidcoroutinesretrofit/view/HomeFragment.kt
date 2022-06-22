package com.devtides.androidcoroutinesretrofit.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtides.androidcoroutinesretrofit.R
import com.devtides.androidcoroutinesretrofit.databinding.FragmentHomeBinding
import com.devtides.androidcoroutinesretrofit.viewmodel.ListViewModel
import com.devtides.coroutinesretrofit.view.CountryListAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: ListViewModel by viewModels()
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.btnStart.setOnClickListener {
            viewModel.refresh()
        }

        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })

        viewModel.countryLoadError.observe(viewLifecycleOwner, Observer { isError ->
            binding.listError.visibility = if (isError == null) View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
            }
        })
    }
}