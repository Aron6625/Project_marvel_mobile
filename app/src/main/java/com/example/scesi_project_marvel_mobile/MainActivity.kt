package com.example.scesi_project_marvel_mobile

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.scesi_project_marvel_mobile.databinding.ActivityMainBinding
import com.example.scesi_project_marvel_mobile.startframe.FragmentStartFrame
import com.example.scesi_project_marvel_mobile.viewmodel.character.FragmentCharacterList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContent.id, FragmentStartFrame())
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.character_menu -> {
                return navigateToFragment(FragmentCharacterList())
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun navigateToFragment(frag: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContent.id, frag)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }
}
