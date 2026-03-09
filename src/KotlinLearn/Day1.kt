package KotlinLearn

fun main(){
    println("My name is DJUU")
    println("I am 23 year Old")
    Maths()
    MonthAge()
    RectangleArea()
}


fun Maths(){
    println(25*3)
}

fun MonthAge(){
    println(" My Age in months Old:" + 22 * 12)
}

fun RectangleArea(){
    var length = 20
    var ht = 10

    var Area = length * ht
    println(Area)
}