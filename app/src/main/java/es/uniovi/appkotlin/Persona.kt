package es.uniovi.appkotlin

class Persona(private val age: Int)
{
    fun conduce(fn:(Int) -> Boolean) = fn(age)
}