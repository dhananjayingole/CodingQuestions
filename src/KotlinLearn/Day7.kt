package KotlinLearn

fun main(){
    val studentNames = mutableMapOf<Int, String>()

    val studentGrades = mutableMapOf<Int, MutableList<Int>>()

    println("Adding Sample Students")

//    Student1:
    studentNames[1001] = "Dhananjay Ingole"
    studentGrades[1001] = mutableListOf(82,90,78,92,88)

    // Student 2: Bob
    studentNames[1002] = "Bob Smith"
    studentGrades[1002] = mutableListOf(75, 82, 79, 91, 84)

    // Student 3: Charlie
    studentNames[1003] = "Charlie Brown"
    studentGrades[1003] = mutableListOf(95, 93, 97, 89, 94)

    // Student 4: Diana
    studentNames[1004] = "Diana Prince"
    studentGrades[1004] = mutableListOf(65, 70, 72, 68, 71)

    println("✅ Added ${studentNames.size} students")

    // Display what we added
    displayAllStudents(studentNames, studentGrades)
}

fun displayAllStudents(names: Map<Int, String>, grades: Map<Int, List<Int>>) {
    println("\n" + "-".repeat(50))
    println("📋 ALL STUDENTS")
    println("-".repeat(50))

    // Using what we learned:
    // Day 3: String templates
    // Day 5: Iterating through maps
    // Day 4: Array/list operations

    for ((id, name) in names) {
        val studentGrades = grades[id] ?: emptyList()

        // String template with formatting
        println("ID: $id | Name: $name")
        println("   Grades: ${studentGrades.joinToString()}")

        // Calculate average using what we learned Day 4
        if (studentGrades.isNotEmpty()) {
            val average = studentGrades.average()
            println("   Average: ${"%.2f".format(average)}")

            // Get letter grade using when (Day 6)
            val letterGrade = getLetterGrade(average)
            println("   Letter Grade: $letterGrade")
        } else {
            println("   No grades recorded")
        }
        println()
    }
}

fun getLetterGrade(average: Double):String{
    return when (average){
        in 90.0..100.0 -> "A 🌟"
        in 80.0..89.9 -> "B 👍"
        in 70.0..79.9 -> "C 😐"
        in 60.0..69.9 -> "D ⚠️"
        else -> "F ❌"
    }
}