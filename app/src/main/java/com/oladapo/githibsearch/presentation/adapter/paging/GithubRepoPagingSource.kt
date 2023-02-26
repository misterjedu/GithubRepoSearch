package com.oladapo.githibsearch.presentation.adapter.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oladapo.githibsearch.data.network.ApiService
import com.oladapo.githibsearch.data.network.model.Item
import com.oladapo.githibsearch.data.network.model.RepoData
import retrofit2.HttpException

class GithubRepoPagingSource(
    private val apiService: ApiService,
    private val query: String,
) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val currentPage = params.key ?: 1
            val response: RepoData = apiService.getRepos(query, currentPage)
            LoadResult.Page(
                data = response.items,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
