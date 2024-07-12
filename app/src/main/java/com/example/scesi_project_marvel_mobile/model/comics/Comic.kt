package com.example.scesi_project_marvel_mobile.model.comics

import com.example.scesi_project_marvel_mobile.model.characters.Thumbnail
import com.example.scesi_project_marvel_mobile.model.characters.Url


data class Comic (val id: Int, val title: String, val description: String,
                  val thumbnail: Thumbnail, val urls: List<Url>, val images : List<Image>,
                  val characters: CharacterList, val creators: CreatorList )

data class CharacterList (val items: List<CharaterSummary>)

data class CharaterSummary(val resourceUri: String, val role: String, val name: String)

data class CreatorList(val items : List<CreatorSummary>)

data class CreatorSummary(val resourceUri: String, val role: String, val name: String)