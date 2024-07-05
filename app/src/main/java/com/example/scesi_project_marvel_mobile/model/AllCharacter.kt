package com.example.scesi_project_marvel_mobile.model

import com.google.gson.annotations.SerializedName

class AllCharacter {
    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: String,

    @SerializedName("total")
    val total: String,

    @SerializedName("count")
    val count: String,

    @SerializedName("results")
    val results: List<DataCharacter>

}