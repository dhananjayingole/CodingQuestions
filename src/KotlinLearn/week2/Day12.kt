package KotlinLearn.week2

//lambda and Higher Order Functions

//1. Lambda Functions - Functions Without Names
// Regular function
fun add(a: Int, b: Int): Int {
    return a + b
}

// Lambda equivalent
val addLambda = { a: Int, b: Int -> a + b }

// Usage
val result = addLambda(3, 5)  // 8