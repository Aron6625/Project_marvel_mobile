package com.example.scesi_project_marvel_mobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val controller by lazy { findNavController(R.id.nav_graph_fragment) }
    private val viewModel: StateAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller.addOnDestinationChangedListener { _,
                                                     destination,
                                                     _ ->
            title = destination.label
            viewModel.componentes.observe(this, Observer {
                it?.let { temComponentes ->
                    if (temComponentes.appBar) {
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                    if (temComponentes.bottomNavigation) {
                        bottom_nav.visibility = View.VISIBLE
                    } else {
                        bottom_nav.visibility = View.GONE
                    }
                }
            })
        }
        bottom_nav.setupWithNavController(controller)
    }
}