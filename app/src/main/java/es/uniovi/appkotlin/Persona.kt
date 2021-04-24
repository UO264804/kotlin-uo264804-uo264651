package es.uniovi.appkotlin

class Persona(private val age: Int)
{
    //Aqui no nos hace falta constructor porque al añadir val en el parametro que se le pasa a la clase ya lo toma como propiedad
    //val age : Int = age
    //init{this.age = age}

    //Ejemplo de funcion de orden superior
    fun conduce(fn:(Int) -> Boolean) = fn(age)

    //Ejemplo usando typealias
    fun drive(fn:license) = fn(age)
}

//Typealias example
//Es muy potente cuando utilizamos tipos muy complejos
typealias license = (Int) -> Boolean