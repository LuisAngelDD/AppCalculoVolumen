package com.example.navigationcomponent.activity

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.*
import com.example.navigationcomponent.R

class MainActivity : AppCompatActivity() {
    //@SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()//ocultar el action var
        //ya no se hace nada a qui el metodo activity solo funciona como un contenedor para los fragments nunca dejamos el activity
        //Figuras fragment es la vista principal
    }
}