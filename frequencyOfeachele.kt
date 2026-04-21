fun frequency(arr: IntArray): List<List<Int>> {
    // 1. Create an empty list to store our final answer
    val ans = mutableListOf<List<Int>>()
    
    // 2. Create a map to keep track of counts (Key = Number, Value = Frequency)
    val mpp = mutableMapOf<Int, Int>()
    
    // 3. Loop through every number in the input array
    for (num in arr) {
        // Check if we have seen this number before
        val currentCount = mpp[num]
        
        if (currentCount == null) {
            // If it's the first time seeing it, set count to 1
            mpp[num] = 1
        } else {
            // Otherwise, increase the existing count by 1
            mpp[num] = currentCount + 1
        }
    }
    
    // 4. Move data from the Map into our List of Lists
    for (entry in mpp) {
        val pair = listOf(entry.key, entry.value)
        ans.add(pair)
    }
    
    return ans
}

fun main() {
    // 1. Create a beginner-friendly array
    val myNumbers = intArrayOf(1, 2, 2, 3, 3, 3, 4)
    
    // 2. Call the frequency function and store the result
    val result = frequency(myNumbers)
    
    // 3. Print the result
    println("Frequency of numbers:")
    println(result)
}
