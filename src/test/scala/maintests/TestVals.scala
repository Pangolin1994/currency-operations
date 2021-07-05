package maintests

import currencies.{Dollar, Euro, Ruble}
import scala.math.BigDecimal


object TestVals {
  val di: Dollar[Int] = Dollar(2)
  val dd: Dollar[Double] = Dollar(4.2)
  val ed: Euro[Double] = Euro(2.8)
  val rd: Ruble[Double] = Ruble(120.5)

  def formatDouble(value: Double): Double = BigDecimal(value)
    .setScale(2, BigDecimal.RoundingMode.HALF_UP)
    .toDouble
}