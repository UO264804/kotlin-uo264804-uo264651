package es.uniovi.appkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.uniovi.appkotlin.databinding.ActivityAddHobbiesBinding


class AddHobbiesActivity : AppCompatActivity()
{
    lateinit var binding: ActivityAddHobbiesBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHobbiesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAdd.setOnClickListener {
            val dat: String = binding.nombreHobbie.text.toString()
            val returnIntent = Intent()
            returnIntent.putExtra("nombreHobbie", dat)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}