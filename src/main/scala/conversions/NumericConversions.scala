package conversions


object NumericConversions {
  implicit def doubleToInt(d: Double): Int = d.toInt
  implicit def doubleToLong(d: Double): Long = d.toLong
  implicit def longToInt(l: Long): Int = l.toInt
}