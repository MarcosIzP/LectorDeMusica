package com.tfg.lectordemusica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//siempre que queramos injectar una dependencia que hayamos creado con Dagger-Hilt, y que sea de tipo Componente de Android,
// deberemos crear la anotacion de entrada de Android
//@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}