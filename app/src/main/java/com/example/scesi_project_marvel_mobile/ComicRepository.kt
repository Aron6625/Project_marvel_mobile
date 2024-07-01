package com.example.scesi_project_marvel_mobile

class ComicRepository(private val apiService: MarvelApiService) {
    suspend fun fetchComics(): List<Co  mic>? {
        val response = apiService.getComics("YOUR_PUBLIC_KEY", "1", "GENERATED_HASH")
        if (response.isSuccessful) {
            return response.body()?.data?.results
        }
        return null
    }
}