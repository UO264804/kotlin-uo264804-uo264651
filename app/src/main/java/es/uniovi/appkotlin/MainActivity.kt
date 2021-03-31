package es.uniovi.appkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import es.uniovi.appkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    var Tag:String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.cambiarVentana.setOnClickListener {
            Toast.makeText(this, "se ha pulsado el boton de cambiar ventana", Toast.LENGTH_SHORT).show()
        }
        binding.sendLogin.setOnClickListener {
            val userLogin: String = binding.login.text.toString()
            var intent:Intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("user_login",userLogin)
            Toast.makeText(this, "se ha pulsado el boton de login", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        
    }
}