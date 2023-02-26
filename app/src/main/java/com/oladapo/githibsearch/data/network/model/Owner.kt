package com.oladapo.githibsearch.data.network.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("avatar_url")
    val avatarImage: String,
    @SerializedName("repos_url")
    val repositoryUrl: String,
)
