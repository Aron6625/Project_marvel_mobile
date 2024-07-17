package com.example.scesi_project_marvel_mobile.viewmodel.character

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.scesi_project_marvel_mobile.R
import com.example.scesi_project_marvel_mobile.databinding.OneCharacterViewBinding
import com.example.scesi_project_marvel_mobile.model.MarvelHandler
import com.example.scesi_project_marvel_mobile.model.characters.Character
import com.example.scesi_project_marvel_mobile.model.characters.CharacterDataContainer
import com.example.scesi_project_marvel_mobile.model.characters.CharacterDataWrapper
import com.example.scesi_project_marvel_mobile.viewmodel.AdapterExpandableListView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentViewOneCharacter : Fragment() {

    companion object {
        fun newInstance(id: Int): Fragment {
            val fragment = FragmentViewOneCharacter()
            val bundle = Bundle()
            bundle.putInt("index", id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: OneCharacterViewBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = OneCharacterViewBinding.inflate(inflater, container, false)
        val view = binding.root
        val id = requireArguments().getInt("index")
        var url = ""

        (activity as? AppCompatActivity)?.supportActionBar?.hide()


        val expandableListView = binding.expandableListView

        MarvelHandler.service.getOneCharacter(id)
            .subscribeOn(Schedulers.io())
            .retry(10)
            .onErrorReturn {
                println("error : ${it.message}")
                CharacterDataWrapper(CharacterDataContainer(emptyList()))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                val character = wrapper.data.results[0]
                binding.characterTitle.text = character.name

                binding.characterSummary.text = if (character.description.isEmpty())
                    "No available description" else character.description

                Picasso.with(view.context).load(character.thumbnail.path + "." + character.thumbnail.extension)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(binding.characterImage, object : com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            binding.progressViewOneCharacterImage.visibility = View.GONE
                        }

                        override fun onError() {
                            // Handle the error
                        }
                    })
                url = character.urls[1].url
                createExpandableLv(getData(character), expandableListView)
            }



        return view
    }

    private fun getData(character: Character): HashMap<String, MutableList<String>> {
        val comicList: MutableList<String> = mutableListOf()
        val seriesList: MutableList<String> = mutableListOf()
         for (item in character.comics.items) {
             comicList.add(item.name)
         }
         for (item in character.series.items) {
             seriesList.add(item.name)
         }

        val mapOfCharacterExpLv = HashMap<String, MutableList<String>>()
        mapOfCharacterExpLv["Comics"] = comicList
        mapOfCharacterExpLv["Series"] = seriesList

        return mapOfCharacterExpLv
    }

    private fun createExpandableLv(
        listData: HashMap<String, MutableList<String>>,
        expandableListView: ExpandableListView?
    ) {
        expandableListView?.let {
            val titleList = ArrayList(listData.keys)
            val adapter = AdapterExpandableListView(
                requireContext(),
                titleList,
                listData
            )

            it.setAdapter(adapter)

            it.setOnGroupExpandListener {
            }

            it.setOnGroupCollapseListener {
            }

            it.setOnChildClickListener { _, _, _, _, _ ->
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
