package com.oladapo.githibsearch.domain.repository

import androidx.paging.PagingData
import com.oladapo.githibsearch.data.network.model.Item
import kotlinx.coroutines.flow.Flow


interface GithubRepository {
    suspend fun getRepositories(query: String): Flow<PagingData<Item>>
}
