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
        var view = binding.root
        setContentView(view)

        binding.cambiarVentana.setOnClickListener {

            Log.d(Tag,"pulsamos un boton")
            Toast.makeText(this, "se ha pulsado el boton de cambiar ventana", Toast.LENGTH_SHORT).show()
        }
        binding.sendLogin.setOnClickListener {
            var user_login: String = binding.login.text.toString()

            var intent:Intent = Intent(this,SecondActivity::class.java)

            intent.putExtra("user_login",user_login)

            startActivity(intent)

        }
        binding.shareInfo.setOnClickListener {
            var message = "hola soy ${binding.login.toString()}"
            var intent = Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent,"Compartir con: "))

        }
        
    }
}