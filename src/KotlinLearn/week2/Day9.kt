package KotlinLearn.week2

//inheritance-> properties of one class can be used by other using open keyword for inheritance

open class Animals{
    fun sound(){
        println("Animals are lovely")
    }

    open fun sound2(){
        println("Animal sound is cute.")
    }
}

class Dog: Animals()

//overriding-> Child class can change parent behavior.
//here it shows children function called instead of parent as it's override the function.
class Cat: Animals(){
    override fun sound2(){
        println("Cat sound is meow meow")
    }
}

fun main(){
    val d = Dog()
    d.sound()

    val c = Cat()
    c.sound2()
}