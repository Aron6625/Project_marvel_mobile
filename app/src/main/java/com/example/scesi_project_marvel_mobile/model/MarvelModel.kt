package com.example.scesi_project_marvel_mobile.model

data class MarvelModel (
    val id: Int,
    val title: String,
    val description: String,
    val pageCount: Int,
    val prices: List<Price>,
    val thumbnail: Image,
    val creators: CreatorList
)
data class Price(
    val type: String,
    val price: Float
)
data class Image(
    val path: String,
    val extension: String
) {
    fun getFullImagePath() = "$path.$extension"
}
data class CreatorList(
    val available: Int,
    val collectionURI: String,
    val items: List<Creator>,
    val returned: Int
)

data class Creator(
    val resourceURI: String,
    val name: String,
    val role: String
)