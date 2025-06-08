fun CheckAngle(hours:Int, minutes:Int): Double{
if(hours < 0 ||minutes <0 || hours > 12 || minutes > 50){
println("Invalid Input")
}

val hour = if( hours == 12) 0 else hours
val minutes = if(minutes == 60) 0 else minutes

val minuteAngle = 6 * minutes
val hourAngle = 30 * hour + 0.5 * minutes

val angle = abs(hourAngle - minuteAngle)
return min(angle, 360 - angle)
}

fun main() {
    val hours = 3
    val minutes = 30
    val angle = CheckAngle(hours, minutes)
    println("Angle at $hours:$minutes = $angle degrees")
}
