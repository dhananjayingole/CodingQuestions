package KotlinLearn

fun main(){
//    different_looping()
    loop_through_Maps()
}

fun different_looping(){
    println("1..5 includes 5")
    for(i in 1..5) print("$i")
    println()

//    Excluding end(until)
    println("until 5 excludes 5:")
    for(i in 1 until 5) print("$i")
    println()

    // Characters
    println("\nCharacters from 'a' to 'e':")
    for (c in 'a'..'e') print("$c ")  // a b c d e
    println()

    // Reverse characters
    println("\nCharacters from 'z' down to 'v':")
    for (c in 'z' downTo 'a') print("$c ")  // z y x w v
    println()
}

//looping through Collections
fun loop_through_collections(){
    val fruits = listOf("Apple","Banana","Orange","Mango","Grape")
    val numbers = intArrayOf(10, 20, 30, 40, 50)

    for(fruit in fruits){
        println("I Like $fruit")
    }

    for(i in fruits.indices){
        println("Index $i: ${fruits[i]}")
    }
}

fun loop_through_Maps(){
    val ages = mapOf(
        "Alice" to 25,
        "Bob" to 30,
        "Charlie" to 35,
        "Diana" to 28
    )

    for(entry in ages){
        println("${entry.key} is ${entry.value} years old")
    }

    println("\n--- Looping with destructuring ---")
    for ((name, age) in ages) {
        println("$name is $age years old")
    }

    println("\n--- Looping through keys ---")
    for (name in ages.keys) {
        println("Name: $name")
    }

    println("\n--- Looping through values ---")
    for (age in ages.values) {
        println("Age: $age")
    }
}

//While loops

fun while_loop(){
    var count = 1
    while (count <= 5){
        count++
    }

    var counter = 5
    while (counter > 0){
        counter--
    }
}