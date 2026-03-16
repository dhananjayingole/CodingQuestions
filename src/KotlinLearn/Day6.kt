package KotlinLearn

fun main(){
//    Ranges_usage()
//    if_else()
//    Operator_Working()
    when_statement()
}

fun if_else(){
    val age = 18

    if(age >= 18){
        println("You are an Adult")
    }

//    Or
    val status = if(age >= 18) "adult" else "minor"
    println(status)
    println("This runs no matter what")
}

fun Operator_Working(){
        val a = 10
        val b = 5
        val c = 10

        println("--- Comparison Operators ---")
        println("a = $a, b = $b, c = $c\n")

        println("a == c : ${a == c}")    // true  (equal to)
        println("a != b : ${a != b}")    // true  (not equal to)
        println("a > b  : ${a > b}")     // true  (greater than)
        println("a < b  : ${a < b}")     // false (less than)
        println("a >= c : ${a >= c}")    // true  (greater than or equal)
        println("a <= b : ${a <= b}")    // false (less than or equal)
}

fun when_statement(){
    val day = 3

    when(day){
        1-> println("Monday")
        2-> println("Tuesday")
        3-> println("Wednesday")
        4-> println("Thursday")
        5-> println("Friday")
        6-> println("Saturday")
        7-> println("Sunday")
        else -> println("Invalid Day!")
    }
}

fun Ranges_usage(){
    val scores = 85

    when(scores){
        in 80..100 -> println("A - Excellent!")
        in 80..89 -> println("B-> Good job!")
        in 70..79-> println("C -> Not bad")
        in 60..69-> println("D -> Need improvement")
        in 0.. 59 -> println("F-> Failed")
        else -> println("Invalid score")
    }
}

fun temperature_Convertor(){
    println("Temperature Converter & Advisor")

    val celsius = 25.0
    val fahrenheit = celsius * 9/5 + 32

    println("$celsius°C = $fahrenheit°F")

    val advice = when {
        celsius < 0 -> "🥶 Freezing! Stay indoors!"
        celsius < 10 -> "🧥 Cold! Wear a jacket!"
        celsius < 20 -> "😊 Cool. A light sweater is fine."
        celsius < 30 -> "🌞 Warm! Nice weather!"
        celsius < 35 -> "🔥 Hot! Stay hydrated!"
        else -> "🥵 Very hot! Stay in AC!"
    }

    println("Advice : $advice")

    val isBeachWeather = celsius in 25.0.. 32.0
    println("Beach weather? ${if (isBeachWeather) "Yes! " else "Not really"}")
}