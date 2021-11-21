package com.dam2a.simondice

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
    // Instaciamos las variables del layout
    var rondaTextView: TextView? = null
    var tituloTextView: TextView? = null

    //Inicamos un indice  para poder acceder a los elementos de los arraylist comprobar y secuencia
    var indice: Int = 0
    // Declaramos una variable de control para que no rompa el programa si el usuario pulsa cualquier boton antes del boton jugar
    var jugarPulsado = false

    // Iniciamos una variable de control para comprobar la secuencia
    var resultado: Boolean = true

    // Declaramos variables nulas de tipo Button para después añadirles el id de los botones del layout
    var empezarJugar: Button? = null


    // Declaramos listas mutables para agregar la secuencia de los botones y los botones que se han pulsado
    val arrayBotones = hashMapOf<Int, Button>()
    val mensajeUsuario = hashMapOf<Int, String>()
    var secuencia: MutableList<Int> = arrayListOf()
    var comprobar: MutableList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        // Asignamos el id del TQ   TextView ronda a la variable rondaTextView
        rondaTextView = findViewById(R.id.ronda)
        //Asigamos a la variable tituloTexView el id del textView titulo
        tituloTextView = findViewById(R.id.titulo)
        //Asignamos a la varibale empezarJugar el id del boton jugar
        empezarJugar = findViewById(R.id.jugar)

        // Asignamos el id de los botones de colores a unas variables
        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)

        // Añdimos los botones al hashMap
        arrayBotones[0] = rojo
        arrayBotones[1] = verde
        arrayBotones[2] = amarillo
        arrayBotones[3] = azul

        // Añadimos los mensajes al hashMap
        mensajeUsuario[0]= getString(R.string.repetir)
        mensajeUsuario[1]= getString(R.string.fin)
        mensajeUsuario[2]= getString(R.string.pulsaJugar)

        empezarJugar?.setOnClickListener {
            jugarPulsado = true
            //Hacemos visible el titulo Ronda cuando el jugador pulsa jugar
            tituloTextView?.visibility = TextView.VISIBLE
            //Hacemos visible el número de la ronda cuando el jugador pulsa jugar
            rondaTextView?.visibility = TextView.VISIBLE
            //Hacemos invisible el boton jugar
            empezarJugar?.visibility = Button.INVISIBLE
            mostrarRonda()


        }

        rojo.setOnClickListener {
            if (jugarPulsado==true) {
                comprobar.add(0)
                indice = comprobar.size - 1
                resultado = comprobar[indice] == secuencia[indice]

                if (comprobar.size == ronda) {
                    comprobarSecuencia()
                }
                if (!resultado && comprobar.size != ronda) {
                    comprobarSecuencia()
                }
            }else{
                Toast.makeText(this,mensajeUsuario[2],Toast.LENGTH_SHORT).show()
            }

            }

        verde.setOnClickListener {
            if(jugarPulsado==true) {
                comprobar.add(1)
                indice = comprobar.size - 1
                resultado = comprobar[indice] == secuencia[indice]

                if (comprobar.size == ronda) {
                    comprobarSecuencia()
                }
                if (!resultado && comprobar.size != ronda) {
                    comprobarSecuencia()
                }
            }else{
                Toast.makeText(this,mensajeUsuario[2],Toast.LENGTH_SHORT).show()

            }
        }
        amarillo.setOnClickListener {
            if (jugarPulsado==true) {
                comprobar.add(2)
                indice = comprobar.size - 1
                resultado = comprobar[indice] == secuencia[indice]

                if (comprobar.size == ronda) {
                    comprobarSecuencia()
                }
                if (!resultado && comprobar.size != ronda) {
                    comprobarSecuencia()
                }
            }else{
                Toast.makeText(this,mensajeUsuario[2],Toast.LENGTH_SHORT).show()

            }

        }
        azul.setOnClickListener {
            if (jugarPulsado==true) {
                comprobar.add(3)
                indice = comprobar.size - 1
                resultado = comprobar[indice] == secuencia[indice]

                if (comprobar.size == ronda) {
                    comprobarSecuencia()
                }
                if (!resultado && comprobar.size != ronda) {
                    comprobarSecuencia()
                }
            }else{
                Toast.makeText(this,mensajeUsuario[2],Toast.LENGTH_SHORT).show()

            }
        }
        Log.d("Estado", "Botontes comprobados")


    }

    private fun mostrarRonda() {

        //Incrementamos una unida la ronda cada vez que se ejecute el metodo mostrarRonda
        ronda++
        //Le enviamos la ronda incrementada al TextView para que se muestre
        rondaTextView?.text = ronda.toString()
        Log.d("Estado", "Mostrando ronda $ronda")
        // Ejecutamos la secuencia
        ejecutarSecuencia()
    }

    private fun ejecutarSecuencia() {
        Log.d("Estado", "Ejecutando secuencia")

        GlobalScope.launch(Dispatchers.Main) {
            secuenciaBotones()
        }
        Log.d("Estado", "Secuencia ejecutada")
        Toast.makeText(this,mensajeUsuario[0],Toast.LENGTH_SHORT).show()
    }


    suspend fun secuenciaBotones() {

        val colores = arrayOf("#D72B00", "#64DD17", "#FFAB00", "#304FFE")
        val random = (0..3).random()
        secuencia.add(random)
        val tamanho = ronda - 1
        for (i in 0..tamanho) {
            delay(500)
            arrayBotones[secuencia[i]]?.setBackgroundColor(Color.WHITE)
            delay(500)
            arrayBotones[secuencia[i]]?.setBackgroundColor(Color.parseColor(colores[secuencia[i]]))

        }


    }

    private fun comprobarSecuencia() {
        Log.d("Estado", "Comprobando secuencia")
        if (!resultado) {
            Toast.makeText(this,mensajeUsuario[1],Toast.LENGTH_SHORT).show()
            // Ponemos la ronda a 0 por que el juego se termino
            ronda = 0
            rondaTextView?.text = ronda.toString()

            // Reseteamos los arrays para poder volver a jugar
            secuencia = arrayListOf()
            comprobar = arrayListOf()
            // Hacemos visible el botón jugar
            empezarJugar?.visibility = Button.VISIBLE

        } else {
            // Resteamos el arraylist comprobar cada nueva ronda
            comprobar = arrayListOf()
            mostrarRonda()


        }
        Log.d("Estado", "Secuencia comprobada")

    }


    }
