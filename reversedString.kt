fun reverseUsingBuiltIn(str: String): String {
return str.reversed()
}

fun reverseUsingLoop(str: String): String {
  var reveresed = ""
  for(i in str.length -1 down To 0){
    reveresed +=str[i]
  }
  return reveresed
}


fun main() {
val original = "Hello, Kotlin!"
println("LOOPMethod:" + reverseUsingLoop(original))
println("Built-in reversed(): " + reverseUsingBuiltIn(original))
}
