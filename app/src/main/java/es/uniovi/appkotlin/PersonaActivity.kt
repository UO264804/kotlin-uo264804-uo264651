package es.uniovi.appkotlin

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import es.uniovi.appkotlin.databinding.ActivityPersonaBinding
import es.uniovi.appkotlin.databinding.ActivitySecondBinding

class PersonaActivity : AppCompatActivity()
{
    lateinit var binding: ActivityPersonaBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.editTextNumber.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                // if the event is a key down event on the enter button
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    // perform action on key press
                    verficarConduccion()

                    // hide soft keyboard programmatically
                    hideSoftKeyboard()

                    // clear focus and hide cursor from edit text
                    binding.editTextNumber.clearFocus()
                    binding.editTextNumber.isCursorVisible = false
                    return true
                }
                return false
            }
        })
    }

    // extension function to hide soft keyboard programmatically
    // Con esto la clase activity puede utilizar este metodo como si fuera de su propia clase
    fun Activity.hideSoftKeyboard(){
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }}

    fun verficarConduccion() {
        var persona : Persona = Persona(Integer.parseInt(binding.editTextNumber.text.toString()))

        binding.londres.setTextColor(Color.parseColor("#FF0000"));
        binding.kentuky.setTextColor(Color.parseColor("#FF0000"));
        binding.dakota.setTextColor(Color.parseColor("#FF0000"));
        binding.asturias.setTextColor(Color.parseColor("#FF0000"));

        if(persona.conduce(::London))
        {
            binding.londres.setTextColor(Color.parseColor("#00FF00"));
        }

        if(persona.conduce(::Kentucky))
        {
            binding.kentuky.setTextColor(Color.parseColor("#00FF00"));
        }

        if(persona.conduce(::Dakota))
        {
            binding.dakota.setTextColor(Color.parseColor("#00FF00"));
        }

        if(persona.conduce(::Asturias))
        {
            binding.asturias.setTextColor(Color.parseColor("#00FF00"));
        }
    }
}

//Ejemplo de funciones usadas en primer orden
//Ejemplo de funcion pasada de normal a utilizacion de expresiones
fun London(age:Int):Boolean
{
  return age >= 17
}
//fun London(age: Int):Boolean = age >= 17
//fun London(age: Int) = age >= 17

//Cuidado, anteriormente al ser un ejemplo sencillo nos infiere que tipo de retorno pero podria causar otros problemas
fun prueba(f : (Int) -> Int, g : (Int) -> Int):(Int)->Int
{
    return {x -> f(g(x))}
}

fun prueba2(f : (Int) -> Int, g : (Int) -> Int) : (Int)->Int = {x -> f(g(x))}

//Aqui no puede inferir el tipo de X como en prueba dos porque hemos quitado el tipo de retorno que es de donde lo obtenia
fun prueba3(f : (Int) -> Int, g : (Int) -> Int) = {x:Int -> f(g(x))}


//Ejemplos de funciones utilizadas como expresiones
fun Kentucky(age: Int) = age >= 16

fun Dakota(age: Int) = age >= 14

fun Asturias(age: Int) = age >= 18
