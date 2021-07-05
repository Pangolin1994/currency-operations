package currencies

import converters.{Converter, TagConverter}
import CurrencyTag._
import scala.math.Numeric
import scala.Numeric.Implicits._


case class Ruble[A: Numeric] private (override val value: A) extends Currency(value, RubleTag) {
  override def +[B: Numeric, R, Tag1 <: CurrencyTag](other: Currency[B, Tag1])
                            (implicit conv: Converter.Aux[A, B, R], R: Numeric[R],
                             tagConverter: Option[TagConverter[B, A, Tag1, RubleTag.type]]): Ruble[R] =
    tagConverter match {
      case Some(tagConv) => Ruble[R](conv.fromA(value) + conv.fromA(tagConv.convert(other).value))
      case None => Ruble[R](conv.fromA(value) + conv.fromB(other.value))
    }

  override def -[B: Numeric, R, Tag1 <: CurrencyTag](other: Currency[B, Tag1])
                            (implicit conv: Converter.Aux[A, B, R], R: Numeric[R],
                             tagConverter: Option[TagConverter[B, A, Tag1, RubleTag.type]]): Ruble[R] =
    tagConverter match {
      case Some(tagConv) => Ruble[R](conv.fromA(value) - conv.fromA(tagConv.convert(other).value))
      case None => Ruble[R](conv.fromA(value) - conv.fromB(other.value))
    }

  override def *[B: Numeric, R](factor: B)
                      (implicit conv: Converter.Aux[A, B, R], R: Numeric[R]): Ruble[R] =
    Ruble[R](conv.fromA(value) * conv.fromB(factor))

  override def /[B: Numeric, R](factor: B)
                               (implicit conv: Converter.Aux[A, Double, R], R: Numeric[R]): Ruble[R] = {
    val newFactor = 1 / factor.toDouble
    Ruble[R](conv.fromA(value) * conv.fromB(newFactor))
  }

  override def canEqual(that: Any): Boolean = that.isInstanceOf[Ruble[_]]

  override def equals(that: Any): Boolean =
    that match {
      case that: Ruble[_] =>
        that.canEqual(this) && this.value == that.value
      case _ => false
    }

  override def toString: String = f"${value.toDouble}%2.2f rubles"
}
