package KotlinLearn.Weak3.Day15


//Sealed Classes and Enum Classes.
//used for ->
//1. State Management(loading, Success, error States)
//2.Navigation events(screen transitions)
//3.Result wrappers(Success/Failure)
//4.UI States(viewStates in MVVM)
//5 Type-safe callbacks

//Real -world Android Enums
enum class NetworkStatus {
    CONNECTED, CONNECTING, DISCONNECTED, ERROR
}

enum class UserRole(val permissions: List<String>) {
    ADMIN(listOf("read","write","delete","manage_users")),
    EDITOR(listOf("read","write")),
    VIEWER(listOf("read")),
    GUEST(listOf("read_limited"));

    fun canDelete(): Boolean = permissions.contains("delete")
    fun canWrite(): Boolean = permissions.contains("write")

}

enum class LoadingState {
    IDLE, LOADING, SUCCESS, ERROR
}

enum class ScreenOrientation{
    PORTRAIT, LANDSCAPE, SQUARE
}

enum class SortOption {
    NAME_ASC, NAME_DESC, DATE_ASC, DATE_DESC, POPULARITY;

    fun toQueryParam(): String = when (this){
        NAME_ASC->"name_asc"
        NAME_DESC->"name_desc"
        DATE_ASC->"date_asc"
        DATE_DESC->"date_desc"
        POPULARITY->"popularity"
    }
}

enum class Priority {
    LOW, MEDIUM, HIGH, URGENT
}

// Usage
fun getPriorityColor(priority: Priority): String {
    return when (priority) {
        Priority.LOW -> "🟢"
        Priority.MEDIUM -> "🟡"
        Priority.HIGH -> "🟠"
        Priority.URGENT -> "🔴"
    }
}

// Enum with properties
enum class Status(val code: Int, val description: String) {
    SUCCESS(200, "Operation completed successfully"),
    PENDING(202, "Operation is pending"),
    ERROR(500, "Operation failed"),
    NOT_FOUND(404, "Resource not found");

    fun isSuccessful(): Boolean = this == SUCCESS

    fun getFormattedMessage(): String = "[$code] $description"
}

