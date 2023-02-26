package com.oladapo.githibsearch.data.network.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("description")
    val description: String,
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
)
