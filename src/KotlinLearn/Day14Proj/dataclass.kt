package KotlinLearn.Day14Proj

data class Student(
    val id: Int,
    val name: String,
    val email: String?,
    val phone: String?,
    val enrollmentYear: Int
)

data class Course(
    val code: String,
    val name: String,
    val credits: Int,
    val department: String
)

data class Grade(
    val studentId: Int,
    val courseCode: String,
    val score: Double,
    val semester:String
){
    val letterGrade: String by lazy {
        when {
            score >= 90 -> "A+"
            score >= 85 -> "A"
            score >= 80 -> "A-"
            score >= 75 -> "B+"
            score >= 70 -> "B"
            score >= 65 -> "B-"
            score >= 60 -> "C+"
            score >= 55 -> "C"
            score >= 50 -> "D"
            else -> "F"
        }
    }

    val gradePoint: Double
        get() = when {
            score >= 90 -> 4.0
            score >= 85 -> 4.0
            score >= 80 -> 3.7
            score >= 75 -> 3.3
            score >= 70 -> 3.0
            score >= 65 -> 2.7
            score >= 60 -> 2.3
            score >= 55 -> 2.0
            score >= 50 -> 1.0
            else -> 0.0
        }
}
