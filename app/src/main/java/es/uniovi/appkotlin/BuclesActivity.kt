package es.uniovi.appkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import es.uniovi.appkotlin.databinding.ActivityBuclesBinding
import es.uniovi.appkotlin.databinding.ActivityMainBinding

class BuclesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuclesBinding
    private lateinit var t: TextView
    private lateinit var t2: TextView
    private lateinit var t3: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuclesBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        t =   binding.t1
        t2 =   binding.t2
        t3 = binding.t3
       bucles()
    }

    fun bucles(){
        var st1:String = ""
        var st2:String = ""
        var st3:String = ""
        for (i in 1..6){
            print(i)
           st1 =  st1.plus(i) //para concatenar strings existe la funcion plus
        }
        for (i in 6 downTo 0 step 2){
            print(i)
           st2 = st2.plus(i) //para concatenar strings existe la funcion plus
        }
        for (i in 1 until 10) {       // i in [1, 10), 10 is excluded
            print(i)
            st3 =  st3.plus(i)
        }

        t.setText(st1.toCharArray(),0, st1.toCharArray().size)
        t2.setText(st2.toCharArray(),0,st2.toCharArray().size)
        t3.setText(st3.toCharArray(),0,st3.toCharArray().size)
    }
}