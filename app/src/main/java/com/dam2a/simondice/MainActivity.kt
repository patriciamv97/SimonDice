package com.dam2a.simondice

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.String.valueOf
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Asigno a la variable empezarJugar el id del boton jugar
        val empezarJugar: Button = findViewById(R.id.jugar)
        //Cuando clikas el botón se muestra la ronda
        var ronda : Int =0
        empezarJugar.setOnClickListener {
            print("a")
            mostrarRonda(ronda , empezarJugar)
            ejecutarSecuencia()

        }

    }


    private fun mostrarRonda(ronda:Int , boton : Button) {
        val Titulo : TextView = findViewById(R.id.titulo)
        Titulo.visibility = TextView.VISIBLE
        var Ronda : TextView = findViewById(R.id.ronda)
        Ronda.visibility = TextView.VISIBLE
       // boton.visibility = TextView.INVISIBLE
        var contador = ronda +1
        print(contador)
        Ronda.text = valueOf(contador)
        Log.d("Estado", "Mostrando ronda")
    }

    private fun ejecutarSecuencia() {
        Log.d("Estado", "Ejecutando secuencia")
        Log.d("Estado", "Secuencia ejecutada")
        Toast.makeText(this, "repite la secuencia", Toast.LENGTH_SHORT).show()
    }
}