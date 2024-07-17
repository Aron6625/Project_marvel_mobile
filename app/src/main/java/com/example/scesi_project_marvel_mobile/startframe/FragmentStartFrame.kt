package com.example.scesi_project_marvel_mobile.startframe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.scesi_project_marvel_mobile.MainActivity
import com.example.scesi_project_marvel_mobile.databinding.StartViewBinding
import com.example.scesi_project_marvel_mobile.viewmodel.character.FragmentCharacterList

class FragmentStartFrame : Fragment() {

    private var _binding: StartViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StartViewBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        binding.characterBg.setOnClickListener {
            val activity = activity as MainActivity
            activity.navigateToFragment(FragmentCharacterList())
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
