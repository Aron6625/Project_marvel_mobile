package com.example.scesi_project_marvel_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var contador =  0
    private lateinit var button:Button
    private lateinit var contadorText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.contador_button)
        contadorText = findViewById(R.id.contador_texto)
        button.setOnClickListener(){
            contador ++
            updateText()
        }
    }
    fun updateText(){
        contadorText.text = contador.toString()
    }
}