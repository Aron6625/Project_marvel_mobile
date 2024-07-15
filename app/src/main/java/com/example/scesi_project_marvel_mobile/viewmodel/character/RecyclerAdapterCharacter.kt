package com.example.scesi_project_marvel_mobile.viewmodel.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scesi_project_marvel_mobile.databinding.CardForListCharacterBinding
import com.example.scesi_project_marvel_mobile.model.characters.Character

class RecyclerAdapterCharacter(
    var characters: MutableList<Character>,
    private val clickListener: (Character) -> Unit
) : RecyclerView.Adapter<RecyclerAdapterCharacter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CardForListCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position], clickListener)
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(private val binding: CardForListCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character, clickListener: (Character) -> Unit) {
            binding.characterName.text = character.name
            binding.root.setOnClickListener { clickListener(character) }
        }
    }
}
