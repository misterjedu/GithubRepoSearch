package com.oladapo.githibsearch.data.network

import com.oladapo.githibsearch.data.network.model.RepoData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int = 30,
    ): RepoData
}
