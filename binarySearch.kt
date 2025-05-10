fun binarySearch(arr : IntArray, target: Int): Int {
    var lo = 0
    var hi = arr.size - 1

    while(lo<=hi){
        val mid = lo + (hi-lo)/2

        when{
            arr[mid] ==target ->return mid
            arr[mid]< target ->lo = mid+1
            else-> hi = mid-1
        }
    }
    return -1
}

fun main(){
    val sortedArray = intArrayOf(1,3,5,7,9,11,13)
    val target = 7
    val result = binarySearch(sortedArray, target)

    if(result != -1){
        println("Ele found at index $result")
    } else{
        println("ele not found")
    }
}
