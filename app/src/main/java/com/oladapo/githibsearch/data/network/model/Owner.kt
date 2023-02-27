package com.oladapo.githibsearch.data.network.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val avatarImage: String,
    @SerializedName("url")
    val repositoryUrl: String,

)
