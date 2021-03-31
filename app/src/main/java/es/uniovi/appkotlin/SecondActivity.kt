package es.uniovi.appkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import es.uniovi.appkotlin.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        Log.d("seguimiento","antes del bundle")
        var bundle : Bundle ? = intent.extras
        Log.d("seguimiento","despues del bundle")

        var login: String? = bundle!!.getString("user_login")
        var texto = "bienvenido$login"
        binding.bienvenida.setText(texto)

    }
}