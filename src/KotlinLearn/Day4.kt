package KotlinLearn

import kotlin.math.max

fun main(){
//    createArray()
//    ArrayWithConstructor()
//    NullArray()
//    iteratethroughArray()
//    iteratethroughArray()
    twoDimMatrix()
    val result = linearSearch()
    println("Target found: $result")
}

fun createArray(){
    val numbers = arrayOf(1,2,3,4,5)

    val intArray = intArrayOf(1, 2, 3, 4, 5)        // IntArray
    val doubleArray = doubleArrayOf(1.1, 2.2, 3.3) // DoubleArray
    val booleanArray = booleanArrayOf(true, false, true) // BooleanArray
    val charArray = charArrayOf('a', 'b', 'c')      // CharArray
    val byteArray = byteArrayOf(1, 2, 3)            // ByteArray
    val longArray = longArrayOf(100L, 200L, 300L)   // LongArray
    val floatArray = floatArrayOf(1.5f, 2.5f, 3.5f) // FloatArray
    val shortArray = shortArrayOf(1, 2, 3)          // ShortArray

    val fruits = arrayOf("Apple","banana","Cheeku")

    println(numbers) // will print weired Stuff
    println(numbers.joinToString()) // this will print correctly
}

fun ArrayWithConstructor(){
    val doubled = Array(5){
        i -> i*2
    }
    println(doubled.joinToString())
    val squares = Array(5){
        i->i*1
    }
    println(squares.joinToString())
}

//Null and Empty Array
fun NullArray(){
    val empty = emptyArray<String>()
    println(empty.joinToString())
    println(empty.size)

    val nullArray = arrayOfNulls<String>(3)
    println(nullArray.joinToString())
}

fun iteratethroughArray(){
    val fruits = arrayOf("Apple", "WaterMelon", "Arjun", "DJU")

//  without idx.
    for(fruit in fruits){
        println(fruit)
    }

//  with index
    for ((index, fruit) in fruits.withIndex()) {
        println("$index: $fruit")
    }

//    using forEach
    fruits.forEach { fruit ->
        println(fruit)
    }

//    using forEachIndexed loop
    fruits.forEachIndexed { index, fruit ->
        println("[$index] = $fruit")
    }
}

fun linearSearch(): Boolean {
    val numbers = intArrayOf(10, 20, 30, 40, 50)
    val target = 30

    for (integer in numbers) {
        if (integer == target) {
            return true  // Found the target
        }
    }
    return false  // Target not found after checking all elements
}

fun twoDimMatrix(): Int {
    val matrix = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(4,5,6),
        intArrayOf(7,8,9)
    )

    var sum = 0
    val rows = matrix.size
    val cols = matrix[0].size

//    Sort each row in Descending order.
    for(i in 0 until rows){
        matrix[i].sortDescending()
    }

//    traverse col wise
    for(j in 0 until cols){
        var maxCol = 0
        for(i in 0 until rows){
            maxCol = maxOf(maxCol, matrix[i][j])
        }
        sum+= maxCol
    }
    return sum
}