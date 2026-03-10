package KotlinLearn

fun main(){
//    StringDiffFormat()
//    loopThroughStr()
//    StringEquality()
//    StringFunctions()
    EmailChecker()
}



fun StringDiffFormat(){
    val normal = "Hello World"
    val withNewLine = "First line\nsecond line"  // \n = new line
    val withTab = "Column1\tColumn2"            // \t = tab
    val withQuote = "She said \"Hello\""        // \" = quote inside string

    val raw = """This is a raw string
        It can span multiple lines
        And keeps all spaces and indentations
        You can use "quotes" without escaping
        And even ${'$'} symbols without problems!""".trimMargin()


    println(normal)
    println("---")
    println(withNewLine)
    println(withTab)
    println(withQuote)
    println(raw)
}

fun loopThroughStr(){
    val word = "HELLO"

//    without index
    println("Print each Character:")
    for(char in word){
        println(Char)
    }
//    eith index
    println("\nWith index:")
    for ((index, char) in word.withIndex()) {
        println("Position $index: $char")
    }
}

fun StringEquality(){
    val str1 = "hello"
    val str2 = "hello"
    val str3 = "HELLO"

    println(str1 == str2)
    println(str3.length)
    println(str1 == str3)

//    to ignore case:
    println(str1.equals(str3, ignoreCase = true))
}

fun StringFunctions(){
    val text = "I love Kotlin programming"

    println(text.startsWith("I love"))     // true
    println(text.endsWith("programming"))  // true
    println(text.contains("Kotlin"))       // true
    println(text.isEmpty())                 // false
    println(text.isNotEmpty())              // true

    val blank = "   "
    println(blank.isBlank())                // true (only whitespace)
    println(blank.isEmpty())                 // false (has spaces)

    println(text.substring(0, 6))     // "Kotlin" (positions 0 to 5)
    println(text.substring(7))         // "Programming Language" (from 7 to end)

    println(text.take(6))              // First 6 characters: "Kotlin"
    println(text.takeLast(8))           // Last 8 characters: "Language"
    println(text.drop(7))               // Remove first 7: "Programming Language"
}

fun EmailChecker() {
    val email = "user@example.com"

    val hasAt = email.contains("@")
    val hasDot = email.contains(".")
    val atPosition = email.indexOf("@")
    val lastDotPosition = email.lastIndexOf(".")

    println("--- Email Check: $email ---")
    println("Contains @: $hasAt")
    println("Contains .: $hasDot")

    if (hasAt && hasDot) {
        println("Has @ at position: $atPosition")
        println("Has . after @: ${lastDotPosition > atPosition}")

        if (atPosition > 0 && lastDotPosition > atPosition + 1 && lastDotPosition < email.length - 1) {
            println("✓ Email looks valid!")
        } else {
            println("✗ Email has issues")
        }
    } else {
        println("✗ Email missing @ or .")
    }
}