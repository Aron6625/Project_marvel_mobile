package com.example.scesi_project_marvel_mobile


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.scesi_project_marvel_mobile.databinding.ActivityMainBinding
import com.projects.disav.marvelissimo.databinding.ActivityMainBinding
import com.projects.disav.marvelissimo.ui.Startframe.FragmentStartFrame
import com.projects.disav.marvelissimo.ui.searchresults.characters.FragmentCharacterList
import com.projects.disav.marvelissimo.ui.searchresults.comics.FragmentComicList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            navigateToFragment(FragmentStartFrame())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.comic_menu -> {
                navigateToFragment(FragmentComicList())
                true
            }
            R.id.character_menu -> {
                navigateToFragment(FragmentCharacterList())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContent.id, frag)
            .addToBackStack(null)
            .commit()
    }
}
