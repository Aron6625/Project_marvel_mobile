package com.example.scesi_project_marvel_mobile.startframe


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.start_view.view.*
import android.support.v7.app.AppCompatActivity
import com.example.scesi_project_marvel_mobile.MainActivity
import com.example.scesi_project_marvel_mobile.R
import com.example.scesi_project_marvel_mobile.viewmodel.character.FragmentCharacterList



class FragmentStartFrame: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.start_view, container, false)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        view.character_bg.setOnClickListener {
            var activity = activity as MainActivity

            activity.navigateToFragment(FragmentCharacterList())
        }

//        view.comic_bg.setOnClickListener{
//            var activity = activity as MainActivity
//
//            activity.navigateToFragment(FragmentComicList())
//        }


        return view
    }


}