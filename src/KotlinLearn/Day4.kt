package KotlinLearn

fun main(){
//    createArray()
//    ArrayWithConstructor()
    NullArray()
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