package es.uniovi.appkotlin

import android.app.Activity
import android.content.Context
import android.widget.Toast

//usamos tambien parametros for defecto -> algo que java no tiene pero kotlin si ♫
fun Context.showToast(message: String, duration:Int = Toast.LENGTH_SHORT){ //adds showToast to Context class
    Toast.makeText(this, message, duration).show()
//PARA PODER ACCEDER DESDE EL ADAPTER TENDRIAMOS QUE HACERLO DESDE EL CONTEXT
//LA ACTIVIDAD PUEDE ACCEDER TAMBIEN PORQUE ACTIVITY ES UNA SUBCLASS DE Context!!!
}