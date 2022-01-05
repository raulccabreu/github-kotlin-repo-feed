package com.example.githubktrepofeed.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubktrepofeed.RepositoriesApplication
import com.example.githubktrepofeed.data.RepositoriesData
import com.example.githubktrepofeed.data.network.retrofit.RetrofitService
import com.example.githubktrepofeed.databinding.RepositoriesFragmentBinding

class RepositoriesFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoriesFragment()
    }

    private lateinit var viewModel: RepositoriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = RepositoriesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val repositoriesAdapter = RepositoriesAdapter()
        binding.adapter = repositoriesAdapter
        viewModel.repositories.observe(viewLifecycleOwner, {
            it.let(repositoriesAdapter::submitList)
        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, RepositoriesViewModelFactory(
                (context?.applicationContext as RepositoriesApplication).repositoriesData)
        )[RepositoriesViewModel::class.java]
    }

}