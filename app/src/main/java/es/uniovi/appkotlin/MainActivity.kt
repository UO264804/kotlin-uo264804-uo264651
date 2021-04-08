package es.uniovi.appkotlin

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.os.Looper.loop
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import es.uniovi.appkotlin.databinding.ActivityMainBinding
import java.security.Permission
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*
import java.util.concurrent.CancellationException

class MainActivity : AppCompatActivity()
{
    companion object{
        val TAG: String = MainActivity::class.java.simpleName
    }

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000 // ms
    private lateinit var job: CompletableJob
    private lateinit var binding: ActivityMainBinding
    var tag:String = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        //setSupportActionBar(binding.myToolbar)

        binding.funcionesOrdenes.setOnClickListener {
            var intent:Intent = Intent(this,PersonaActivity::class.java)
            startActivity(intent)
        }

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
        binding.corutinas.setOnClickListener {

            if(!::job.isInitialized){
                initjob()
            }
           binding.pBar.startJobOrCancel(job)

        }
        binding.corutinas2.setOnClickListener {
            var intent:Intent = Intent(this,CorutinasActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)=   when (item.itemId) {

        R.id.action_settings-> {
            showToast("ajustes, en proximas versiones")
            true
        }

        R.id.action_camara -> {
            showToast("ajustes, en proximas versiones")
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }

    }

    fun resetjob(){
        if(job.isActive || job.isCompleted){
            job.cancel(CancellationException("Resetting job"))
        }
        initjob()
    }

    fun initjob(){
        binding.corutinas.setText("Start Job #1")
        updateJobCompleteTextView("")
        job = Job()
        job.invokeOnCompletion {
            it?.message.let{
                var msg = it
                if(msg.isNullOrBlank()){
                    msg = "Unknown cancellation error."
                }
                Log.e(TAG, "${job} was cancelled. Reason: ${msg}")
                showToast(msg)
            }
        }
        binding.pBar.max = PROGRESS_MAX
        binding.pBar.progress = PROGRESS_START
    }


    fun ProgressBar.startJobOrCancel(job: Job){
        if(this.progress > 0){
            Log.d(TAG, "${job} is already active. Cancelling...")
            resetjob()
        }
        else{
            binding.corutinas.setText("Cancel Job #1")
            CoroutineScope(IO + job).launch{
                Log.d(TAG, "coroutine ${this} is activated with job ${job}.")

                for(i in PROGRESS_START..PROGRESS_MAX){
                    delay((JOB_TIME / PROGRESS_MAX).toLong())
                    this@startJobOrCancel.progress = i
                }
                updateJobCompleteTextView("Job is complete!")
            }
        }
    }
//siempre actualizar desde el hilo principal -> scope main
    private fun updateJobCompleteTextView(text: String){
        GlobalScope.launch (Main){
            showToast(text)
        }
    }

    private fun showToast(text: String){
        GlobalScope.launch (Main){
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}