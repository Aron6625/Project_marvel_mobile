package com.example.scesi_project_marvel_mobile
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.scesi_project_marvel_mobile.R.*
import com.example.scesi_project_marvel_mobile.startframe.FragmentStartFrame
import com.example.scesi_project_marvel_mobile.viewmodel.character.FragmentCharacterList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.fragment_content, FragmentStartFrame())
        transaction.commit()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.comic_menu -> {
//
//                return navigateToFragment(FragmentComicList())
//            }
            id.character_menu ->{
                return navigateToFragment(FragmentCharacterList())
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun navigateToFragment(frag : Fragment): Boolean{
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.fragment_content, frag)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }



}
