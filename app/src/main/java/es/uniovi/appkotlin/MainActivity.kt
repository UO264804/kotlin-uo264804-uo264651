package es.uniovi.appkotlin

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import es.uniovi.appkotlin.databinding.ActivityMainBinding
import java.security.Permission

class MainActivity : AppCompatActivity()
{
    companion object{
        val TAG: String = MainActivity::class.java.simpleName
        val OPEN_CAMERA=200
        val REQUEST_CAMERA_PERMISSION=300
    }
    private lateinit var binding: ActivityMainBinding
    var tag:String = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        setSupportActionBar(binding.myToolbar)
        var ig = binding.imageView;

        binding.cambiarVentana.setOnClickListener {
            showToast(resources.getString(R.string.btn_cambiarVentana)) //usando extension de kotlin
            Log.i(TAG,"se ha pulsado el boton de A BuclesActivity")
           var intent:Intent = Intent(this,BuclesActivity::class.java)
            startActivity(intent)
        }

        binding.sendLogin.setOnClickListener {
            showToast(resources.getString(R.string.btn_login_toast))
            Log.i(TAG,"se ha pulsado el boton de login")
            var userLogin: String = binding.login.text.toString()
            var intent:Intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(Constants.USER_LOGIN,userLogin) //usando el fichero AppConstraints
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





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }





    override fun onOptionsItemSelected(item: MenuItem)=   when (item.itemId) {

        R.id.action_settings-> {
            true
        }

        R.id.action_camara -> {
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }

    }


}