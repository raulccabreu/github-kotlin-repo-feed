package com.example.githubktrepofeed.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubktrepofeed.RepositoriesApplication
import com.example.githubktrepofeed.databinding.RepositoriesFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.pagingDataFlow.collect {
                repositoriesAdapter.submitData(it)
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, RepositoriesViewModelFactory(
                (context?.applicationContext as RepositoriesApplication).repositoriesData)
        )[RepositoriesViewModel::class.java]
    }

}