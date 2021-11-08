package com.dam2a.simondice

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    //Inicamos contador para comprobar la ronda
    var indice : Int =0
    // Iniciamos una variable de control para comprobar la secuencia
    var resultado :Boolean = true
    // Asignamos el id de los botones a una variable
    var empezarJugar: Button? = null
    var rojo: Button? = null
    var amarillo: Button? = null
    var verde: Button? = null
    var azul: Button? = null
    val arrayBotones = hashMapOf<Int, Button>()
    val secuencia: MutableList<Int> = arrayListOf<Int>()
    val comprobar: MutableList<Int> = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        empezarJugar = findViewById(R.id.jugar)
        arrayBotones[0] = findViewById(R.id.rojo)
        arrayBotones[1] = findViewById(R.id.verde)
        arrayBotones[2] = findViewById(R.id.amarillo)
        arrayBotones[3] = findViewById(R.id.azul)

        //Cuando clikas el botón se muestra la ronda

        empezarJugar?.setOnClickListener {
            mostrarRonda()
            ejecutarSecuencia()
            comprobarSecuencia(secuencia,comprobar)

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
        empezarJugar?.visibility = Button.INVISIBLE
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


    suspend fun secuenciaBotones() {

        val colores = arrayOf("#D72B00", "#64DD17", "#FFAB00", "#304FFE")
        val random = (0..3).random()
        secuencia.add(random)
        val tamanho = ronda - 1
        for (i in 0..tamanho) {
            Log.d("estado", "" + secuencia[i])
            delay(500)
            arrayBotones[secuencia[i]]?.setBackgroundColor(Color.WHITE)
            delay(500)
            arrayBotones[secuencia[i]]?.setBackgroundColor(Color.parseColor(colores[secuencia[i]]))

        }


    }

    private fun comprobarSecuencia(secuencia: MutableList<Int>, comprobar: MutableList<Int>) {
        Log.d("Estado", "Comprobando secuencia")

       rojo?.setOnClickListener {
           comprobar.add(0)

           resultado = comprobar[indice] == secuencia[indice]
           indice++
       }
       verde?.setOnClickListener {
           comprobar.add(1)

           resultado = comprobar[indice] == secuencia[indice]
           indice++
       }
       amarillo?.setOnClickListener{
           comprobar.add(2)
           resultado = comprobar[indice] == secuencia[indice]
           indice++

       }
       azul?.setOnClickListener {
           comprobar.add(3)
           resultado = comprobar[indice] == secuencia[indice]
           indice++
       }
        if(!resultado){
            Toast.makeText(this,"Fin del juego",Toast.LENGTH_SHORT).show()
            ronda =0
            empezarJugar?.visibility = Button.VISIBLE


        }else{
            ejecutarSecuencia()
        }
        Log.d("Estado", "Secuencia comprobada")

    }


}