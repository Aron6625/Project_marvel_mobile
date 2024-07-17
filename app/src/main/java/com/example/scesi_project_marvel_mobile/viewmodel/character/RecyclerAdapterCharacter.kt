package com.example.scesi_project_marvel_mobile.viewmodel.character

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scesi_project_marvel_mobile.R
import com.example.scesi_project_marvel_mobile.databinding.CardForListCharacterBinding
import com.example.scesi_project_marvel_mobile.model.characters.Character
import com.squareup.picasso.Picasso

class RecyclerAdapterCharacter(
    var characters: MutableList<Character> = mutableListOf(),
    private val clickListener: (Character) -> Unit
) : RecyclerView.Adapter<RecyclerAdapterCharacter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = CardForListCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val itemCharacter = characters[position]
        holder.bindCharacter(itemCharacter, clickListener)
    }

    class ItemHolder(private val binding: CardForListCharacterBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var character: Character? = null

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            character?.let {
                // Handle click
            }
        }

        fun bindCharacter(character: Character, clickListener: (Character) -> Unit) {
            this.character = character
            val uri = "${character.thumbnail.path}.${character.thumbnail.extension}"
//            println("Image URL: $uri")
            Picasso.with(binding.root.context).load(uri)
                .error(R.mipmap.ic_launcher)
                .into(binding.characterImageRecyclerview, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
//                        binding.characterImageRecyclerview.visibility = (View.GONE)
                        binding.progressCharacterImage.visibility = View.GONE
                    }

                    override fun onError() {

                    }
                })
            binding.characterName.text = character.name
            binding.root.setOnClickListener { clickListener(character) }
        }
    }
}
