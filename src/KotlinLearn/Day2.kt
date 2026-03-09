package KotlinLearn

// val and var usage

fun main(){
//    valUsge()
//    varUsage()
//    varValueChanging()
//    ActorInfo()
    ShoppingCalculator()

}

fun valUsge(){
//    val is sealed pack can't edit
    val name = "Dhananjay Ingole"
    println(name)
}

fun varUsage(){
    var name = "Raghu Rokda"
    var age:Int = 25
    var city = "Mumbai"
    var salary: Double = 500.50
    var isPoor: Boolean = true

    println("Name of the Student is $name") //with $ template
    println("Age of the Student is $age")
    println("City of the Student is $city")

//    without $template just as a String template
    println("My Name is" + name + "and i am" + age +"Years Old")
    age = 33
    println(age)
}

fun varValueChanging() {
    var score = 0
    println("Start score: $score")

    score = 10
    println("After first level: $score")

    score = score + 5    // Take current score, add 5, put back in box
    println("After bonus: $score")

    score += 20           // Shortcut for score = score + 20
    println("After second level: $score")
}

fun ActorInfo(){
    val name = "Arjun More"
    var age = 25
    val height = 1.65
    val isActor = true
    var movies = 45

//    print the info
    println("--Persons Info--")
    println("Name: $name")
    println("Age: $age")
    println("Height: $height")
    println("Actor: $isActor")
    println("Movies Acted: $movies")

    // Update some information
    println("\n--- One Year Later ---")   // \n creates a new line
    age = 33
    movies += 2

    println("Name: $name")
    println("Age: $age")
    println("Movies acted: $movies")
}

fun ShoppingCalculator(){
    val item = "Coffee"
    val price = 4.50
    val quantity = 3

    val subtotal = price * quantity
    val tax = subtotal * 0.10
    val total = subtotal + tax

    println("--- Shopping Receipt ---")
    println("Item: $item")
    println("Price: $$price")
    println("Quantity: $quantity")
    println("Subtotal: $$subtotal")
    println("Tax (10%): $$tax")
    println("Total: $$total")
}
