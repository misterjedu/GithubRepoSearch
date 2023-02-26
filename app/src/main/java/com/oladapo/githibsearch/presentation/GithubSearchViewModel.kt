package com.oladapo.githibsearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oladapo.githibsearch.data.network.model.Item
import com.oladapo.githibsearch.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubSearchViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    init {
        getRepoList("Android")
    }

    private val _repoPagingList =
        MutableStateFlow<PagingData<Item>>(PagingData.empty())

    var repoPagingList = _repoPagingList


    fun getRepoList(query: String) {
        viewModelScope.launch {
            githubRepository.getRepositories(query).cachedIn(viewModelScope).collect { pagingData ->
                _repoPagingList.value = pagingData
            }
        }
    }
}
