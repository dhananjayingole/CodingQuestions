package KotlinLearn


//null pointer
//In Java, NullPointerException (NPE) is the most common crash.
//Kotlin eliminates this by making nullability explicit in the type system.

// In Java - any String can be null
//String name;  // Could be null - compiler doesn't warn you
//
//// In Kotlin - you DECLARE if something can be null
//var name: String = "John"     // CANNOT be null - SAFE
//var nickname: String? = null  // CAN be null - explicit!

//[1]  ?.let - Execute Only If Not Null
//[2]  ?: - Elvis Operator (Null Coalescing)
//Provides a default value when something is null

//USER PROFILE

data class User(
    val id: Int,
    val name: String,           // Required - cannot be null
    val email: String?,         // Optional - can be null
    val phone: String?,         // Optional - can be null
    val address: Address?       // Optional complex type
)

data class Address(
    val street: String,
    val city: String,
    val zipCode: String?
)

class UserProfileManager{
    fun displayUserInfo(user: User){
        // Safe call with let
        user.email?.let{ email->
            println("Email: $email")
        }

        // Elvis operator for default value
        val contactMethod = user.phone ?: user.email ?: "No Contact info"
        println("Contact : $contactMethod")

        // Safe call chain for nested properties
        val userCity = user.address?.city ?: "City not provided"
        println("🏙️ City: $userCity")

//        using with multiple safe calls
        val fullAddress = user.address?.let { addr ->
            val zip = addr.zipCode ?: "No ZIP"
            "${addr.street}, ${addr.city} ($zip)"
        } ?: "No address on file"

        println("Address: $fullAddress")
    }

    // Function that validates and processes user
    fun processUser(user: User?):String{
        if(user == null){
            return "❌ No user provided"
        }

        val emailDomain = user.email
            ?.substringAfter('@')
            ?.takeIf { it.isNotEmpty() }
            ?: "Unknown domain"

        return "User ${user.name} from $emailDomain Processed"
    }

    // Safe conversion function
    fun getValidEmail(email: String?): String {
        return email
            ?.takeIf { it.contains('@') }
            ?.trim()
            ?: "default@example.com"
    }
}

fun main(){
    val profileManager = UserProfileManager()

    val completeUser = User(
        id = 1,
        name = "Alice Johnson",
        email = "alice@example.com",
        phone = "+1234567890",
        address = Address("123 Main St", "New York", "10001")
    )

    println("Complete user")
    println(profileManager.displayUserInfo(completeUser))
    println(profileManager.processUser(completeUser))

    println("\n=== Incomplete User ===")
    // User with missing info
    val incompleteUser = User(
        id = 2,
        name = "Bob Smith",
        email = null,      // Missing email
        phone = null,      // Missing phone
        address = null     // Missing address
    )

    profileManager.displayUserInfo(incompleteUser)
    println(profileManager.processUser(incompleteUser))

    println("\n=== Null User ===")
    println(profileManager.processUser(null))

    println("\n=== Email Validation ===")
    println("Valid email: ${profileManager.getValidEmail("john@work.com")}")
    println("Invalid email: ${profileManager.getValidEmail("invalid")}")
    println("Null email: ${profileManager.getValidEmail(null)}")


}