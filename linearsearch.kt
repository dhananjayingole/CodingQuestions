fun linearSearch(arr:IntArray, target: Int): Int{
for(i in arr.indices){
if(arr[i] == target){
return i
}
}
return -1
}

fun main() {
    val numbers = intArrayOf(10, 20, 30, 40, 50)
    val target = 30

    val result = linearSearch(numbers, target)

    if (result != -1) {
        println("Element found at index: $result")
    } else {
        println("Element not found")
    }
}
