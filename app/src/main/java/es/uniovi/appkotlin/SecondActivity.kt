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

        //safe null operations
        // 1) ?.
        // ?.let{ }

        var bundle : Bundle ? = intent.extras

        bundle?.let{
            //asi comprobamos que no es null
            var login: String? = bundle.getString(Constants.USER_LOGIN)
            binding.bienvenida.text = "Bienvenido $login"
        }

    }
}