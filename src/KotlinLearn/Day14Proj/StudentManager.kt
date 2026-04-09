package KotlinLearn.Day14Proj

import kotlin.math.roundToInt


// ============= BUSINESS LOGIC =============
class StudentManager {
    private val students = mutableMapOf<Int, Student>()
    private val courses = mutableMapOf<String, Course>()
    private val grades = mutableListOf<Grade>()
    private var nextStudentId = 1

    // ===== Student Operations =====
    fun addStudent(name: String, email: String?, phone: String?, year: Int): Student {
        val student = Student(nextStudentId, name, email, phone, year)
        students[nextStudentId] = student
        nextStudentId++
        return student
    }

    fun getStudent(id: Int): Student? = students[id]

    fun getAllStudents(): List<Student> = students.values.toList()

    fun updateStudent(id: Int, name: String?, email: String?, phone: String?): Boolean {
        return students[id]?.let { current ->
            students[id] = current.copy(
                name = name ?: current.name,
                email = email ?: current.email,
                phone = phone ?: current.phone
            )
            true
        } ?: false
    }

    fun deleteStudent(id: Int): Boolean {
        return students.remove(id) != null
    }

    // ===== Course Operations =====
    fun addCourse(code: String, name: String, credits: Int, department: String): Course {
        val course = Course(code.uppercase(), name, credits, department)
        courses[code.uppercase()] = course
        return course
    }

    fun getCourse(code: String): Course? = courses[code.uppercase()]

    fun getAllCourses(): List<Course> = courses.values.toList()

    // ===== Grade Operations =====
    fun addGrade(studentId: Int, courseCode: String, score: Double, semester: String): Grade? {
        // Validate student exists
        if (students[studentId] == null) {
            println("❌ Student not found!")
            return null
        }

        // Validate course exists
        if (courses[courseCode.uppercase()] == null) {
            println("❌ Course not found!")
            return null
        }

        // Validate score range
        if (score < 0 || score > 100) {
            println("❌ Score must be between 0 and 100!")
            return null
        }

        val grade = Grade(studentId, courseCode.uppercase(), score, semester)
        grades.add(grade)
        return grade
    }

    fun getStudentGrades(studentId: Int): List<Grade> {
        return grades.filter { it.studentId == studentId }
    }

    fun getCourseGrades(courseCode: String): List<Grade> {
        return grades.filter { it.courseCode == courseCode.uppercase() }
    }

    // ===== Analytics Using Higher-Order Functions =====
    fun calculateGPA(studentId: Int): Double {
        val studentGrades = getStudentGrades(studentId)

        if (studentGrades.isEmpty()) return 0.0

        // Using map and reduce to calculate weighted GPA
        val totalPoints = studentGrades
            .map { grade ->
                val course = courses[grade.courseCode]
                grade.gradePoint * (course?.credits ?: 3)
            }
            .reduceOrNull { sum, points -> sum + points } ?: 0.0

        val totalCredits = studentGrades
            .mapNotNull { courses[it.courseCode]?.credits }
            .reduceOrNull { sum, credits -> sum + credits } ?: 1

        return (totalPoints / totalCredits).roundToInt() / 100.0
    }

    fun getStudentReport(studentId: Int): String {
        val student = students[studentId] ?: return "❌ Student not found!"
        val studentGrades = getStudentGrades(studentId)
        val gpa = calculateGPA(studentId)

        // Using map and joinToString for formatting
        val gradeDetails = studentGrades
            .map { grade ->
                val course = courses[grade.courseCode]
                "  • ${course?.name ?: grade.courseCode}: ${grade.score}% (${grade.letterGrade}) - ${grade.semester}"
            }
            .joinToString("\n")

        return """
            |
            |========================================
            |STUDENT REPORT
            |========================================
            |ID: ${student.id}
            |Name: ${student.name}
            |Email: ${student.email ?: "Not provided"}
            |Phone: ${student.phone ?: "Not provided"}
            |Enrollment Year: ${student.enrollmentYear}
            |
            |📚 COURSES & GRADES:
            |${if (gradeDetails.isNotEmpty()) gradeDetails else "  No grades recorded"}
            |
            |📊 GPA: ${"%.2f".format(gpa)}
            |========================================
        """.trimMargin()
    }

    fun getCourseStatistics(courseCode: String): Map<String, Any>? {
        val course = courses[courseCode.uppercase()] ?: return null
        val courseGrades = getCourseGrades(courseCode.uppercase())

        if (courseGrades.isEmpty()) {
            return mapOf(
                "course" to course,
                "message" to "No grades recorded"
            )
        }

        // Using various collection operations
        val scores = courseGrades.map { it.score }
        val average = scores.average()
        val highest = scores.maxOrNull() ?: 0.0
        val lowest = scores.minOrNull() ?: 0.0
        val passingCount = courseGrades.count { it.score >= 50 }
        val failingCount = courseGrades.count { it.score < 50 }

        // Group grades by letter grade
        val gradeDistribution = courseGrades
            .groupBy { it.letterGrade }
            .mapValues { it.value.size }

        return mapOf(
            "course" to course,
            "average" to average,
            "highest" to highest,
            "lowest" to lowest,
            "passingCount" to passingCount,
            "failingCount" to failingCount,
            "totalStudents" to courseGrades.size,
            "gradeDistribution" to gradeDistribution
        )
    }

    // Using lambda for custom filtering
    fun findStudents(predicate: (Student) -> Boolean): List<Student> {
        return students.values.filter(predicate)
    }

    // Batch operation using higher-order function
    fun batchUpdateGrades(updater: (Grade) -> Grade) {
        grades.indices.forEach { index ->
            grades[index] = updater(grades[index])
        }
    }
}

// ============= UI / MENU SYSTEM =============
class MenuSystem(private val manager: StudentManager) {

    fun start() {
        while (true) {
            printMainMenu()
            when (readLine()?.trim()) {
                "1" -> addStudentMenu()
                "2" -> viewAllStudentsMenu()
                "3" -> addCourseMenu()
                "4" -> addGradeMenu()
                "5" -> viewStudentReportMenu()
                "6" -> viewCourseStatisticsMenu()
                "7" -> searchStudentsMenu()
                "8" -> batchUpdateGradesMenu()
                "9" -> {
                    println("\n👋 Goodbye!")
                    return
                }
                else -> println("\n❌ Invalid option! Please try again.")
            }
        }
    }

    private fun printMainMenu() {
        println("""
            |
            |========================================
            |📚 STUDENT MANAGEMENT SYSTEM
            |========================================
            |1. ➕ Add Student
            |2. 👥 View All Students
            |3. 📖 Add Course
            |4. 📝 Add Grade
            |5. 📊 View Student Report
            |6. 📈 View Course Statistics
            |7. 🔍 Search Students
            |8. 🔄 Batch Update Grades
            |9. 🚪 Exit
            |========================================
            |Choose option: 
        """.trimMargin())
    }

    private fun addStudentMenu() {
        println("\n--- ADD NEW STUDENT ---")

        print("Name: ")
        val name = readLine()?.trim() ?: return

        print("Email (optional): ")
        val email = readLine()?.trim()?.takeIf { it.isNotEmpty() }

        print("Phone (optional): ")
        val phone = readLine()?.trim()?.takeIf { it.isNotEmpty() }

        print("Enrollment Year: ")
        val year = readLine()?.trim()?.toIntOrNull() ?: 2024

        val student = manager.addStudent(name, email, phone, year)
        println("\n✅ Student added successfully! ID: ${student.id}")
    }

    private fun viewAllStudentsMenu() {
        println("\n--- ALL STUDENTS ---")
        val students = manager.getAllStudents()

        if (students.isEmpty()) {
            println("No students found.")
            return
        }

        students.forEach { student ->
            println("ID: ${student.id} | Name: ${student.name} | Year: ${student.enrollmentYear}")
        }
        println("\nTotal students: ${students.size}")
    }

    private fun addCourseMenu() {
        println("\n--- ADD NEW COURSE ---")

        print("Course Code (e.g., CS101): ")
        val code = readLine()?.trim()?.uppercase() ?: return

        print("Course Name: ")
        val name = readLine()?.trim() ?: return

        print("Credits: ")
        val credits = readLine()?.trim()?.toIntOrNull() ?: 3

        print("Department: ")
        val department = readLine()?.trim() ?: "General"

        val course = manager.addCourse(code, name, credits, department)
        println("\n✅ Course added successfully: ${course.code} - ${course.name}")
    }

    private fun addGradeMenu() {
        println("\n--- ADD GRADE ---")

        print("Student ID: ")
        val studentId = readLine()?.trim()?.toIntOrNull() ?: run {
            println("❌ Invalid student ID!")
            return
        }

        print("Course Code: ")
        val courseCode = readLine()?.trim()?.uppercase() ?: return

        print("Score (0-100): ")
        val score = readLine()?.trim()?.toDoubleOrNull() ?: run {
            println("❌ Invalid score!")
            return
        }

        print("Semester (e.g., Fall 2024): ")
        val semester = readLine()?.trim() ?: "Current"

        val grade = manager.addGrade(studentId, courseCode, score, semester)
        if (grade != null) {
            println("\n✅ Grade added successfully! Grade: ${grade.letterGrade}")
        }
    }

    private fun viewStudentReportMenu() {
        println("\n--- STUDENT REPORT ---")
        print("Enter Student ID: ")
        val studentId = readLine()?.trim()?.toIntOrNull()

        if (studentId == null) {
            println("❌ Invalid student ID!")
            return
        }

        println(manager.getStudentReport(studentId))
    }

    private fun viewCourseStatisticsMenu() {
        println("\n--- COURSE STATISTICS ---")
        print("Enter Course Code: ")
        val courseCode = readLine()?.trim()?.uppercase() ?: return

        val stats = manager.getCourseStatistics(courseCode)

        if (stats == null) {
            println("❌ Course not found!")
            return
        }

        val course = stats["course"] as Course
        println("""
            |
            |========================================
            |COURSE STATISTICS: ${course.code} - ${course.name}
            |========================================
        """.trimMargin())

        if (stats.containsKey("message")) {
            println(stats["message"])
        } else {
            println("""
                |📊 Average Score: ${"%.1f".format(stats["average"] as Double)}%
                |🏆 Highest Score: ${stats["highest"]}%
                |📉 Lowest Score: ${stats["lowest"]}%
                |✅ Passing: ${stats["passingCount"]} students
                |❌ Failing: ${stats["failingCount"]} students
                |👥 Total Students: ${stats["totalStudents"]}
                |
                |📈 Grade Distribution:
            """.trimMargin())

            val distribution = stats["gradeDistribution"] as Map<String, Int>
            distribution.forEach { (grade, count) ->
                println("  $grade: $count student${if (count > 1) "s" else ""}")
            }
        }
        println("========================================")
    }

    private fun searchStudentsMenu() {
        println("\n--- SEARCH STUDENTS ---")
        println("Search by: 1. Name | 2. Enrollment Year")

        when (readLine()?.trim()) {
            "1" -> {
                print("Enter name (or part of it): ")
                val query = readLine()?.trim()?.lowercase() ?: return
                val results = manager.findStudents { it.name.lowercase().contains(query) }

                println("\n🔍 Search Results (${results.size} found):")
                results.forEach { student ->
                    println("  ${student.id}: ${student.name} (${student.enrollmentYear})")
                }
            }
            "2" -> {
                print("Enter enrollment year: ")
                val year = readLine()?.trim()?.toIntOrNull() ?: return
                val results = manager.findStudents { it.enrollmentYear == year }

                println("\n🔍 Search Results (${results.size} found):")
                results.forEach { student ->
                    println("  ${student.id}: ${student.name}")
                }
            }
            else -> println("❌ Invalid search option!")
        }
    }

    private fun batchUpdateGradesMenu() {
        println("\n--- BATCH UPDATE GRADES ---")
        println("This will add 5 bonus points to all grades (max 100)")
        print("Confirm? (yes/no): ")

        if (readLine()?.trim()?.lowercase() == "yes") {
            manager.batchUpdateGrades { grade ->
                val newScore = (grade.score + 5).coerceAtMost(100.0)
                grade.copy(score = newScore)
            }
            println("✅ Grades updated successfully!")
        } else {
            println("❌ Operation cancelled.")
        }
    }
}

// ============= MAIN APPLICATION =============
fun main() {
    println("""
        ╔══════════════════════════════════════╗
        ║   WELCOME TO STUDENT MANAGEMENT       ║
        ║         SYSTEM (SMS v1.0)             ║
        ╚══════════════════════════════════════╝
    """.trimIndent())

    val manager = StudentManager()

    // Load sample data
    println("\n📦 Loading sample data...")

    // Add sample students
    manager.addStudent("Alice Johnson", "alice@email.com", "555-0101", 2023)
    manager.addStudent("Bob Smith", "bob@email.com", null, 2024)
    manager.addStudent("Charlie Brown", null, "555-0202", 2023)
    manager.addStudent("Diana Prince", "diana@email.com", "555-0303", 2022)

    // Add sample courses
    manager.addCourse("CS101", "Introduction to Programming", 4, "Computer Science")
    manager.addCourse("MATH201", "Calculus I", 3, "Mathematics")
    manager.addCourse("PHY101", "Physics Fundamentals", 4, "Physics")
    manager.addCourse("ENG101", "English Composition", 3, "Humanities")

    // Add sample grades
    manager.addGrade(1, "CS101", 85.5, "Fall 2023")
    manager.addGrade(1, "MATH201", 92.0, "Fall 2023")
    manager.addGrade(2, "CS101", 76.0, "Fall 2023")
    manager.addGrade(2, "ENG101", 88.5, "Fall 2023")
    manager.addGrade(3, "CS101", 45.0, "Fall 2023")
    manager.addGrade(4, "PHY101", 94.0, "Spring 2023")
    manager.addGrade(4, "MATH201", 88.0, "Spring 2023")

    println("✅ Sample data loaded!\n")

    // Start the menu system
    val menu = MenuSystem(manager)
    menu.start()
}
