package es.uniovi.appkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import es.uniovi.appkotlin.databinding.ActivityCorutinasBinding
import es.uniovi.appkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class CorutinasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCorutinasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityCorutinasBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.boton.setOnClickListener {
            CoroutineScope(IO).launch {
                SimulandoLLamadaApi()
            }

        }
        runBlocking {
        val job =  funcionLaunch()
        job.join()
        val jD :Deferred<String> = async{
            var s: String = ""
            for (i in 1..90){
                s.plus(i)
            }
            s

        }
        val s:String = jD.await()
        showToast(s)

        }


    }
    /*
    NUNCA ACTUALIZAR LA VISTA DESDE UN HILO QUE NO SEA EL PRINCIPAL O MAIN
    SINO PUEDE CRASHEAR LA APP
     */
    private suspend fun setTextOnMainThread(input: String,id_textView:String) {
        withContext (Main) {
           when(id_textView){
               "tv1"->{
                   binding.tv1.setText(input)
               }
               "tv2"->{
                   binding.tv2.setText(input)
               }
           }
        }
    }


    private suspend fun SimulandoLLamadaApi() {
        val result1 = fun1()
        setTextOnMainThread(result1,"tv1")
        val result2 = fun2()
        setTextOnMainThread(result2,"tv2")

    }



    suspend fun funcionLaunch() : Job{
       return  GlobalScope.launch {
            println("comienzo->${Thread.currentThread().name}")
            delay(1000)
            println("final->${Thread.currentThread().name}")
        }
    }

    suspend fun fun1(): String{
        println("fun1->${Thread.currentThread().name}")
        delay(1000)
        return "desde fun1"
    }
    suspend fun fun2(): String{
        println("fun2->${Thread.currentThread().name}")
        delay(1700)
        return "desde fun2"
    }

}