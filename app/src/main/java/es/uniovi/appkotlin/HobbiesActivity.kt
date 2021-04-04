package es.uniovi.appkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.uniovi.appkotlin.databinding.ActivityHobbiesBinding


class HobbiesActivity:AppCompatActivity()
{
    lateinit var binding: ActivityHobbiesBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityHobbiesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

        val adapter = HobbiesAdapter(this, Supplier.hobbies)
        binding.recyclerView.adapter = adapter

        binding.addHobbies.setOnClickListener {
            var intent: Intent = Intent(this, AddHobbiesActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1)
        { // el "1" es el numero que pasaste como parametro

            if (resultCode == RESULT_OK)
            {
                val result = data?.getStringExtra("nombreHobbie")
                // tu codigo para continuar procesando
                var hob = Hobby("Vacio")
                if (result != null)
                {
                    hob = Hobby(result)
                }
                Supplier.hobbies.add(hob)
            }

            if (resultCode == RESULT_CANCELED)
            {
                // código si no hay resultado
                showToast(resources.getString(R.string.codigoCancel)) //usando extension de kotlin
            }
        }
    } //onActivityResult

}