fun main() {
  val matrix = arrayOf(
    intArrayOf(1,2,3),
    intArrayOf(4,5,6),
    intArrayOf(7,8,9)
    )

  val target = 5
  var found = false

  for(i in matrix.indices){
    for(j in matrix[i].indices){
      if(matrix[i][j] == target){
        println("Number $target found at position [$i][$j]")
        found = true
        break
      }
    }
    if(found) break
  }

  if(!found){
    println("Number $target not found in the matrix.")
  }
}
      
