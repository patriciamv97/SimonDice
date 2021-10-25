package com.dam2a.simondice

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.String.valueOf
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    // Inicicamos la ronda
    var ronda : Int = 0
    // Asignamos el id de los botones a una variable
    val empezarJugar: Button = findViewById(R.id.jugar)
    val rojo :Button =findViewById(R.id.rojo)
    val amarillo :Button =findViewById(R.id.amarillo)
    val verde :Button =findViewById(R.id.verde)
    val azul :Button =findViewById(R.id.azul)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Asigno a la variable empezarJugar el id del boton jugar
        val empezarJugar: Button = findViewById(R.id.jugar)
        val rojo :Button =findViewById(R.id.rojo)
        val amarillo :Button =findViewById(R.id.amarillo)
        val verde :Button =findViewById(R.id.verde)
        val azul :Button =findViewById(R.id.azul)

        //Cuando clikas el botón se muestra la ronda

        empezarJugar.setOnClickListener {

            mostrarRonda(empezarJugar)
            ejecutarSecuencia()

        }

    }


    private fun mostrarRonda(boton : Button) {
        //Asigamos a la variable tituloTexView el id del textView titulo
        val tituloTextView : TextView = findViewById(R.id.titulo)
        //Hacemos visible el titulo Ronda cuando el jugador pulsa jugar
        tituloTextView.visibility = TextView.VISIBLE
        // Asignamos el id del TQ   TextView ronda a la variable rondaTextView
        var rondaTextView : TextView = findViewById(R.id.ronda)
        //Hacemos visible el numero de la ronda cuando el jugador pulsa jugar
        rondaTextView.visibility = TextView.VISIBLE
       // boton.visibility = TextView.INVISIBLE
        //Incrementamos una unida la ronda cada vez que se ejecute el metodo mostrarRonda
        ronda++
        //Le enviamos la ronda incrementada al TextView para que se muestre
        rondaTextView.text =ronda.toString()
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
        ronda++
        var arrayBotones = hashMapOf<Int,Button>()
        arrayBotones[0] = rojo
        arrayBotones[1] = verde
        arrayBotones[2] = amarillo
        arrayBotones[3] = azul
        var secuencia :Array <Int> = arrayOf()
        for (i in 2..ronda) {
            var random = (0 until 3).random()
            secuencia = arrayOf(random)
        }
    }
}