package converters


abstract class Converter[A: Numeric, B: Numeric] { self =>
  type Result
  def fromA(a: A): Result
  def fromB(b: B): Result

  def invert: Converter.Aux[B, A, Result] = new Converter[B, A] {
    type Result = self.Result
    def fromA(a: B): Result = self.fromB(a)
    def fromB(b: A): Result = self.fromA(b)
    override def invert: Converter.Aux[A, B, Result] = self
  }
}

object Converter {
  abstract class LeftConverter[A: Numeric, B: Numeric] extends Converter[A, B] {
    type Result = A
    override def fromA(a: A): A = a
  }

  type Aux[A, B, R] = Converter[A, B] { type Result = R }

  implicit def identityConverter[A: Numeric]: LeftConverter[A, A] = new LeftConverter[A, A] {
    override def fromB(b: A): A = b
  }

  implicit val intDoubleConverter: LeftConverter[Double, Int] = new LeftConverter[Double, Int] {
    override def fromB(b: Int): Double = b.toDouble
  }
  implicit val doubleIntConverter: Aux[Int, Double, Double] = intDoubleConverter.invert

  implicit val intLongConverter: LeftConverter[Long, Int] = new LeftConverter[Long, Int] {
    override def fromB(b: Int): Long = b.toLong
  }
  implicit val longIntConverter: Aux[Int, Long, Long] = intLongConverter.invert
}