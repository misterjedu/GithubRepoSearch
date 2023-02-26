package com.oladapo.githibsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oladapo.githibsearch.databinding.GithubSearchFragmentBinding
import com.oladapo.githibsearch.presentation.GithubSearchViewModel
import com.oladapo.githibsearch.presentation.adapter.GithubLoadStateAdapter
import com.oladapo.githibsearch.presentation.adapter.GithubRepoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GithubSearchFragment : Fragment() {

    private var _binding: GithubSearchFragmentBinding? = null

    private val viewModel: GithubSearchViewModel by activityViewModels()

    @Inject
    lateinit var githubRepoAdapter: GithubRepoAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GithubSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.repoPagingList.collectLatest {
                        githubRepoAdapter.submitData(it)
                    }
                }
            }

            bindAdapter(githubRepoAdapter)

            githubRepoAdapter.addLoadStateListener { loadStates ->
                githubRepoRv.isVisible = loadStates.source.refresh is LoadState.NotLoading
                progressSpin.isVisible = loadStates.source.refresh is LoadState.Loading
                retryButton.isVisible = loadStates.source.refresh is LoadState.Error
            }

            retryButton.setOnClickListener {
                githubRepoAdapter.retry()
            }

            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String?): Boolean {
                    if (!text.isNullOrBlank()) {
                        viewModel.getRepoList(text)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun GithubSearchFragmentBinding.bindAdapter(githubRepoAdapter: GithubRepoAdapter) {
    githubRepoRv.adapter = githubRepoAdapter.withLoadStateFooter(
        GithubLoadStateAdapter {
            githubRepoAdapter.retry()
        })
    githubRepoRv.layoutManager = LinearLayoutManager(githubRepoRv.context)
    val decoration = DividerItemDecoration(githubRepoRv.context, DividerItemDecoration.VERTICAL)
    githubRepoRv.addItemDecoration(decoration)
}
