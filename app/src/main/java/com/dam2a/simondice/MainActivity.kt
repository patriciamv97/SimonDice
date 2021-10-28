package com.dam2a.simondice

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    // Inicicamos la ronda
    var ronda: Int = 0

    // Asignamos el id de los botones a una variable
    var empezarJugar: Button? = null
    var rojo: Button? = null
    var amarillo: Button? = null
    var verde: Button? = null
    var azul: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        empezarJugar = findViewById(R.id.jugar)
        rojo = findViewById(R.id.rojo)
        amarillo = findViewById(R.id.amarillo)
        verde = findViewById(R.id.verde)
        azul = findViewById(R.id.azul)
        //Cuando clikas el botón se muestra la ronda

        empezarJugar?.setOnClickListener {

            // mostrarRonda(empezarJugar)
            mostrarRonda()
            ejecutarSecuencia()

        }

    }

    // private fun mostrarRonda(boton : Button) {
    private fun mostrarRonda() {
        //Asigamos a la variable tituloTexView el id del textView titulo
        val tituloTextView: TextView = findViewById(R.id.titulo)
        //Hacemos visible el titulo Ronda cuando el jugador pulsa jugar
        tituloTextView.visibility = TextView.VISIBLE
        // Asignamos el id del TQ   TextView ronda a la variable rondaTextView
        val rondaTextView: TextView = findViewById(R.id.ronda)
        //Hacemos visible el numero de la ronda cuando el jugador pulsa jugar
        rondaTextView.visibility = TextView.VISIBLE
        // boton.visibility = TextView.INVISIBLE
        //Incrementamos una unida la ronda cada vez que se ejecute el metodo mostrarRonda
        ronda++
        //Le enviamos la ronda incrementada al TextView para que se muestre
        rondaTextView.text = ronda.toString()
        Log.d("Estado", "Mostrando ronda " + ronda.toString())
    }

    private fun ejecutarSecuencia() {
        Log.d("Estado", "Ejecutando secuencia")
        val job = GlobalScope.launch(Dispatchers.Main) {
            secuenciaBotones()

        }



        Log.d("Estado", "Secuencia ejecutada")
        Toast.makeText(this, "repite la secuencia", Toast.LENGTH_SHORT).show()
    }



    suspend fun secuenciaBotones(){

        val colores =arrayOf("#EC4849","#008000","#FFFF00","#3498DB")
        val arrayBotones = hashMapOf<Int,Button>()
        arrayBotones[0] =  findViewById(R.id.rojo)
        arrayBotones[1] = findViewById(R.id.verde)
        arrayBotones[2] = findViewById(R.id.amarillo)
        arrayBotones[3] = findViewById(R.id.azul)
        var secuencia :Array <Int> = arrayOf()
        val random = (0 until 3).random()
        secuencia = arrayOf(random)
         for (i in 1..ronda) {
             delay(500)
             arrayBotones[ronda]?.setBackgroundColor(Color.WHITE)
             delay(500)
             arrayBotones[ronda]?.setBackgroundColor(Color.parseColor(colores[ronda]))

        }



    }


}