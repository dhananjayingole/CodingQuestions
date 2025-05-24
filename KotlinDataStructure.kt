//immutable List
fun main(){
  val names: List<String> = listOf("Alice", "BOB", "charlie")
  println("Names: $names")
}

//mutable list
fun main(){
//craeting mutable list
val numbers = mutableListOf(10,20,30,40)
//add an ele.
numbers.add(50)
//removing ele
numbers.removeAt(1)
//update ele.
numbers[2] = 99
//print list
println("Updated List: $numbers")
}

//immutable set
fun main(){
val uniqueNumbers: Set<Int> = setOf(1,2,3,4)
println("unique Numbers: $uniqueNumbers")
}

//mutable set
fun main() {
    val uniqueNames = mutableSetOf("Alice", "Bob", "Charlie")
    uniqueNames.add("Alice") // Won’t add duplicate
    println("Updated Names: $uniqueNames")
}

//immutable map
fun main() {
    val studentGrades: Map<String, Int> = mapOf("Alice" to 85, "Bob" to 90)
    println("Student Grades: $studentGrades")
}

//mutablemap
fun main() {
    val productPrices = mutableMapOf("Laptop" to 50000, "Phone" to 30000)
    productPrices["Phone"] = 32000 // Updating price
    println("Updated Prices: $productPrices")
}

//Queue
fun main(){
val queue: ArrayDeque<Int> = ArrayDeque()
queue.addLast(10)
queue.addLast(20)
queue.removeFirst() //Dequeu
println("Queue After Dequeue: $queue")
}

//Stack
fun main(){
val stack: ArrayDeque<Int> = ArrayDeque()
stack.addLast(10)
stack.addLast(20)
stack.removeLast()//pop
println("Stack After pop: $stack")
}

//LL
class Node(val data: Int, var next: Node? = null)

fun main() {
    val head = Node(10)
    head.next = Node(20)
    head.next?.next = Node(30)

    var current = head
    while (current != null) {
        print("${current.data} -> ")
        current = current.next
    }
}

//grpah
fun main() {
    val graph = mutableMapOf<Int, List<Int>>(
        1 to listOf(2, 3),
        2 to listOf(4, 5),
        3 to listOf(6)
    )
    println("Graph: $graph")
}
