package es.uniovi.appkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.uniovi.appkotlin.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity()
{
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var bundle : Bundle ? = intent.extras
        var login: String? = bundle!!.getString("user_login")
        binding.bienvenida.text = "Bienvenido $login"
    }
}