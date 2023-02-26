package com.oladapo.githibsearch.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.oladapo.githibsearch.data.network.ApiService
import com.oladapo.githibsearch.data.network.model.Item
import com.oladapo.githibsearch.data.network.model.RepoData
import com.oladapo.githibsearch.paging.GithubRepoPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val ITEMS_PER_PAGE = 30

class GithubRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : GithubRepository {
    override suspend fun getRepositories(query: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                GithubRepoPagingSource(apiService = apiService, query = query)
            }
        ).flow
    }
}
