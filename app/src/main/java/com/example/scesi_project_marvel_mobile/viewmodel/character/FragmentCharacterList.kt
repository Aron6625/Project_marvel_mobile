package com.example.scesi_project_marvel_mobile.viewmodel.character

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.scesi_project_marvel_mobile.MainActivity
import com.example.scesi_project_marvel_mobile.R
import com.example.scesi_project_marvel_mobile.databinding.RecyclerviewBinding
import com.example.scesi_project_marvel_mobile.model.MarvelHandler
import com.example.scesi_project_marvel_mobile.model.characters.Character
import com.example.scesi_project_marvel_mobile.model.characters.CharacterDataContainer
import com.example.scesi_project_marvel_mobile.model.characters.CharacterDataWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Locale

class FragmentCharacterList : Fragment() {

    private var _binding: RecyclerviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapterCharacter
    private var searchString: String = ""
    private var results = mutableListOf<Character>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecyclerviewBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.supportActionBar?.show()

        linearLayoutManager = LinearLayoutManager(activity)
        binding.myRecyclerView.layoutManager = linearLayoutManager

        adapter = RecyclerAdapterCharacter(results) { character -> itemClicked(character) }
        binding.myRecyclerView.adapter = adapter

        if (results.isEmpty()) {
            getAllCharacters()
        } else if (savedInstanceState != null) {
            searchString = savedInstanceState.getString("search", "")
            getCharactersNameStartBy(searchString)
        }

        binding.myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && adapter.characters.size >= 20) {
                    if (searchString.isEmpty()) {
                        getAllCharacters(adapter.characters.size)
                    } else {
                        getCharactersNameStartBy(searchString, adapter.characters.size)
                    }
                }
            }
        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @Suppress("DEPRECATION")
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchString = query
                    adapter.characters.clear()
                    results.clear()
                    adapter.notifyDataSetChanged()
                    recyclerVisibility(true)
                    getCharactersNameStartBy(query)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newQuery: String?): Boolean {
                return true
            }
        })
    }

    private fun itemClicked(character: Character) {
        (activity as? MainActivity)?.navigateToFragment(FragmentViewOneCharacter.newInstance(character.id))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("search", searchString)
    }

    @SuppressLint("CheckResult")
    private fun getCharactersNameStartBy(query: String, offset: Int = 0) {
        MarvelHandler.service.getCharactersByNameStartingWith(query.toLowerCase(Locale.ROOT), offset)
            .subscribeOn(Schedulers.io())
            .retry(10)
            .onErrorReturn {
                println("error : ${it.message}")
                CharacterDataWrapper(CharacterDataContainer(emptyList()))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrapper ->
                adapter.characters.addAll(wrapper.data.results)
                results.addAll(wrapper.data.results)
                adapter.notifyDataSetChanged()
                if (results.isEmpty()) {
                    binding.emptyView.text = "No available results for $searchString, try again!"
                    recyclerVisibility(false)
                }
            }, { err -> println("Error: ${err.message}") })
    }

    @SuppressLint("CheckResult")
    private fun getAllCharacters(offset: Int = 0) {
        MarvelHandler.service.getAllCharacters(offset)
            .subscribeOn(Schedulers.io())
            .retry(10)
            .onErrorReturn {
                println("error : ${it.message}")
                CharacterDataWrapper(CharacterDataContainer(emptyList()))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrapper ->
                adapter.characters.addAll(wrapper.data.results)
                results.addAll(wrapper.data.results)
                adapter.notifyDataSetChanged()
            }, { err -> println("Error: ${err.message}") })
    }

    private fun recyclerVisibility(visible: Boolean) {
        binding.myRecyclerView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
