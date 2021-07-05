package conversions


object ConversionConstants {
  val euroToDollarFactor = 1.22
  val dollarToEuroFactor: Double = 1 / euroToDollarFactor

  val dollarToRubleFactor = 73.72
  val rubleToDollarFactor: Double = 1 / dollarToRubleFactor

  val euroToRubleFactor = 89.71
  val rubleToEuroFactor: Double = 1 / euroToRubleFactor
}