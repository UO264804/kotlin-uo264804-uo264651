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
    var tag:String = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.cambiarVentana.setOnClickListener {
            Toast.makeText(this, "Se ha pulsado el boton change window", Toast.LENGTH_SHORT).show()
        }

        binding.sendLogin.setOnClickListener {
            Toast.makeText(this, "Se ha pulsado el boton login", Toast.LENGTH_SHORT).show()
            var userLogin: String = binding.login.text.toString()
            var intent:Intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("user_login",userLogin)
            startActivity(intent)
        }

        binding.shareInfo.setOnClickListener {
            var message = "Hola soy ${binding.login.toString()}"
            var intent = Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent,"Compartir con: "))
        }

        binding.btnRecyclerViewDemo.setOnClickListener {
            var intent:Intent = Intent(this,HobbiesActivity::class.java)
            startActivity(intent)
        }
    }
}