// Basic Question Different Approach.
fun sumOfDigit(n:Int):Int {
  var num = n
  var sum = 0

while (num != 0) {
  sum += num %10
  num /= 10
}

return sum
}


//reverse Digit
fun reverseDigits(n:Int):Int {
  var num = n
  var revNum = 0
  while(num > 0) {
    revNum = revNum * 10 + num % 10
    num = num / 10
  }
  return revNum
}
  //prime num test
// The insight here is:

// If n is divisible by any number greater than √n, then it must also be divisible by some number smaller than √n.

// For example:

// If n = 36, √36 = 6. Its factors include:

// (2, 18), (3, 12), (4, 9), (6, 6)

// Once we check up to 6, we’ve covered all possible factor pairs.

// So instead of checking all numbers till n-1, we check from 2 to √n.
fun isPrime(n : Int) : Boolean{
  var num = n

  if(num <= 1){
    return false
  }

  for(i in 2..sqrt(num.toDouble()).toInt()) {
    if(num % i == 0) return false
  }
  return true
}

//power
fun power(base: Int, exponent: Int): Int {
  return if(exponent == 0) {
    1
  } else {
    base * power(base, exponent -1)
  }
}
