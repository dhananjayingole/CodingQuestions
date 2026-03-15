package KotlinLearn

import jdk.internal.net.http.common.Pair.pair

fun main(){
//    Array_and_ListDiff()
//    List_Operation()
//    Sublist_and_ranges()
//    filtering()
//    Set_Learning()
//    Set_Operations()
//    UserId_with_set()
//    map_create()
//    map_accessing()
//    to_do_project()
    Stock_Management()
}

fun listOperations(){
//    Immutable List ->can't change
    val fruits = listOf("Apple","Banana","Orange","Apple")
    println(fruits) // [Apple, Banana, Orange, Apple]

//    mutable list -> can add, remove and Change
    val mutableFruits = mutableListOf("Apple","Banana","Orange","Apple")
    println(mutableFruits) // [Apple, Banana, Orange, Apple]

//    Empty list
    val emptyList = emptyList<String>()
    val mutableEmpty = mutableListOf<String>()

    // List with specific type
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5)
    val doubles: MutableList<Double> = mutableListOf(1.5, 2.5, 3.5)

    println("Fruits: $fruits")
    println("Numbers: $numbers")
    println("Doubles :$doubles")
}

//Array and List Differenece
fun Array_and_ListDiff(){
    val array = arrayOf(1, 2, 3)
    // array.add(4)  // ❌ ERROR! Arrays can't grow

    // List (can grow and shrink)
    val list = mutableListOf(1, 2, 3)
    list.add(4)  // ✅ Works!
    list.add(5)
    list.remove(2)

    println("Array: ${array.joinToString()}")  // [1, 2, 3] (unchanged)
    println("List: $list")                     // [1, 3, 4, 5] (changed!)
}

//Operation on List
fun List_Operation(){
    val fruits = mutableListOf("Apple", "Banana", "Orange", "Mango", "Grape")

    println(fruits[0])
    println(fruits.get(1))

    // Safe access
    println(fruits.getOrNull(5))     // null
    println(fruits.getOrElse(5) { "Unknown" })  // Unknown

    // First and last
    println(fruits.first())      // Apple
    println(fruits.last())       // Grape

    // Check if element exists
    println("Apple" in fruits)   // true
    println(fruits.contains("Kiwi"))  // false

    // Find index
    println(fruits.indexOf("Orange"))   // 2
    println(fruits.indexOf("Kiwi"))     // -1
    println(fruits.lastIndexOf("Apple")) // 0 (only one Apple)


//    Adding Elements
    fruits.add("Watermelon")
    println("After Adding : $fruits")

    fruits.add(1, "Pear")
    println("After Adding Pear at idx 1: $fruits")

//    Removing Ele
    fruits.remove("Apple")
    println("After Remove of apple: $fruits")

//    updating ele
    fruits[1] = "Chiku"
    println("After Update idx 1: $fruits")
}

//Sublist and Ranges
fun Sublist_and_ranges(){
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val subList = numbers.subList(2,6) // from idx 2 to 5
    println("Sublist (2-5): $subList")

}
fun filtering(){
    val numbers = listOf(1,2,3,4,5,6,7,8,9,10)

    val evenNumbers = numbers.filter { it % 2 == 0}
    println("Even numbers: $evenNumbers")

    val oddNumbers = numbers.filter { it % 2 != 0 }
    println("odd numbers: $oddNumbers")

//    map transformed each element
    val doubled = numbers.map { it * 2 }
    println("Doubled: $doubled")

    val squared = numbers.map { it * it }
    println("Squared: $squared")
}

// Sets ->No Duplicates Allowed here
fun Set_Learning(){
    val fruits = setOf("Apple", "Banana", "Orange", "Apple", "Banana")
    println(fruits)  // [Apple, Banana, Orange]  (duplicates removed!)

    // Mutable set
    val mutableFruits = mutableSetOf("Apple", "Banana", "Orange")
    println(mutableFruits)  // [Apple, Banana, Orange]

    mutableFruits.add("Mango")
    println("Set after Adding Element: $mutableFruits")
}

fun Set_Operations(){
    val set1 = setOf(1,2,3,4,5)
    val set2 = setOf(4,5,6,7,8)

    println("Set1: $set1")
    println("Set2: $set2")

//    union-> all ele from both sets
    val union = set1.union(set2)
    println("Union: $union")

//    Intersection(Common Elements)
    val intersection = set1.intersect(set2)
    println("Intersection: $intersection")
}

fun UserId_with_set() {
    // Example: Unique user IDs
    val userIds = mutableSetOf<Int>()
    userIds.add(1001)
    userIds.add(1002)
    userIds.add(1001)  // Duplicate - ignored!
    userIds.add(1003)

    println("Unique user IDs: $userIds")  // [1001, 1002, 1003]
    println("Total users: ${userIds.size}")  // 3

    // Check if user exists
    val checkId = 1001
    if (checkId in userIds) {
        println("User $checkId exists")
    }
}

//maps-> key-value pair

fun map_create(){
    val ages = mapOf(
        "Arjun" to 20,
        "Brother" to 21,
        "Sadu" to 19
    )
    println(ages)

//    using pair
    val scores = mapOf(
        Pair("Math", 95),
        Pair("Science",88),
        Pair("English",92)
    )
    println(scores)

    // Mutable map
    val mutableAges = mutableMapOf(
        "Alice" to 25,
        "Bob" to 30
    )
    // Empty map
    val emptyMap = emptyMap<String, Int>()
    val mutableEmpty = mutableMapOf<String, Int>()
}

fun map_accessing() {

    val ages = mapOf(
        "Alice" to 25,
        "Bob" to 30,
        "Charlie" to 35
    )

    // Get by key
    println(ages["Alice"])        // 25
    println(ages.get("Bob"))      // 30

    // Safe access
    println(ages["David"])        // null
    println(ages.getOrDefault("David", 0))  // 0
    println(ages.getOrElse("David") { "Not found" })  // Not found

    // Check if key exists
    println("Alice" in ages)      // true
    println(ages.containsKey("Alice"))  // true

    // Check if value exists
    println(ages.containsValue(25))  // true

    // Get all keys and values
    println("Keys: ${ages.keys}")      // [Alice, Bob, Charlie]
    println("Values: ${ages.values}")  // [25, 30, 35]
    println("Entries: ${ages.entries}")  // [Alice=25, Bob=30, Charlie=35]
}

fun map_modifying(){
    val ages = mutableMapOf(
        "Alice" to 25,
        "Bob" to 30
    )
    println("Start: $ages")

    ages["Charlie"] = 35
    println("After adding Charlie: $ages")

    ages.put("David", 40)
    println("After adding David: $ages")

    ages.remove("Charlie")
    println("After Removing Charlie: $ages")
}

fun to_do_project(){
    val tasks = mutableMapOf<String, MutableList<String>>()
    println("Categorized to do list")

    tasks["Work"] = mutableListOf("Finish report", "Email client","Team meeting")
    tasks["Home"] = mutableListOf("Buy groceries","Clean kitchen","Pay bills")
    tasks["Personal"] = mutableListOf("go to gym","Read book","Call mom")

    for ((category, taskList) in tasks){
        println("\n $category")
        for((index, task) in taskList.withIndex()){
            println("${index + 1}.$task")
        }
    }
}

fun Stock_Management(){
    val inventory = mutableMapOf<String, Int>(
        "Apple" to 50,
        "Banana" to 30,
        "Orange" to 40,
        "Milk" to 20,
        "Bread" to 15
    )

    println("--- Store Inventory---")
    println("Current Stock:")
    for((item, quantity) in inventory){
        println(" $item: $quantity units")
    }

//    simulate sales
    val sales = listOf("Apple" to 5, "Banana" to 10, "Milk" to 3, "Apple" to 8)
    println("\n -- Processing Sales---")
    for((item, quantity)  in sales){
        val currentStock = inventory[item]?:0
        if(currentStock >= quantity){
            inventory[item] = currentStock - quantity
            println("Sold $quantity $item. Remaining: ${inventory[item]}")
        }
        else{
            println("Not enough $item! Need $quantity but have $currentStock")
        }
    }
    // Check low stock items
    println("\n--- Low Stock Alert (< 20 units) ---")
    for ((item, quantity) in inventory) {
        if (quantity < 20) {
            println("⚠️ $item: $quantity units - RESTOCK NEEDED")
        }
    }

    // Total items in inventory
    val totalItems = inventory.values.sum()
    println("\nTotal items in inventory: $totalItems")
}