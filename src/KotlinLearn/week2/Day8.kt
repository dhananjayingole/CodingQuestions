package KotlinLearn.week2

//oops in kt

open class Person {
    var name: String = ""
    var age: Int = 0
}

//Another way using constructor
class Animal(var name: String, var Breed: String)


//Adding Function in the Constructor
class Students(var name:String, var age: Int){

    fun greet(){
        println("Hello, My name is $name")
    }
}

//visibility modifier
class BankAccount(private var balance: Int){
    fun deposit(amount: Int){
        balance += amount
    }

    fun checkBalance(){
        println(balance)
    }
}

//homework
open class Employee(
    var name: String,
    private var salary: Int,
    var position:String,
){
    fun increment(amount: Int){
        salary += amount
    }
    fun showSalary(){
        println(salary)
    }
}


fun main(){
    val p = Person()
    p.name = "Dhananjay"
    p.age = 22

    println(p.name)
//calling constructor
    val A1 = Animal("Elephant","African")
    println(A1.name)
    println(A1.Breed)

//    calling function inside the constructor
    val S1 = Students("Arjun", 21)
    println(S1.greet())

    val b1 = BankAccount(50000)

    b1.deposit(20000)
    b1.checkBalance()

//    calling employee class
    val e1 = Employee("Dhanu",1000,"Project Manager")

    e1.increment(2000)
    e1.showSalary()
}