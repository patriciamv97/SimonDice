package com.dam2a.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Asigno a la variable empezarJugar el id del boton jugar
        val empezarJugar :Button = findViewById(R.id.jugar)

        //Cuando clikas el botón se muestra la ronda
        empezarJugar.setOnClickListener{
            mostrarRonda()
        }

    }

    }
    private fun mostrarRonda(){
         Log.d("Estado","Mostrando ronda")
    }
