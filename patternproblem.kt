//half pyramid
fun halfPyaramid(){
val rows = 5
// it means <= rows
for(i in 1..rows) {
  for(j in 1..i){
    print("*")
}
println()
}
}

//left half pyaramid
fun leftHalfPyaramid(){
val rows = 5
// less than rows
for(i in rows downTo 1){
  for(j in 1..rows){
    if(j>= i){
      print("*")
    } else {
      print("")
    }
}
println()
}
}

// number pyaramid 
fun NumberPyaramid(){
val n = 5

println("Sequential Number Pattern:")
var number = 1
for(rows in 0..n){
  for(columns in 0 until rows){
    print("$number")
    number++
  }
println()
}

//Row num pattern
println("\nRow Number Pattern:")
for(rows in 1..n){
  for(columns in 1..rows){
    print("$rows")
  }
println()
}

//col num pattern
println("\nColumn Number Pattern:")
for(rows in 1..n) {
  for(columns in 1..rows) {
    print("$columns")
  }
println()
}
}
