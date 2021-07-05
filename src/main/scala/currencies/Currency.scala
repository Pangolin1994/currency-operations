package currencies

import converters.{Converter, TagConverter}
import scala.math.Numeric
import scala.Numeric.Implicits._


abstract class Currency[A: Numeric, Tag <: CurrencyTag](val value: A, val tag: Tag) {
  def +[B: Numeric, R, Tag1 <: CurrencyTag](other: Currency[B, Tag1])
                   (implicit conv: Converter.Aux[A, B, R], R: Numeric[R],
                    tagConverter: Option[TagConverter[B, A, Tag1, Tag]]): Currency[R, Tag]

  def -[B: Numeric, R, Tag1 <: CurrencyTag](other: Currency[B, Tag1])
                   (implicit conv: Converter.Aux[A, B, R], R: Numeric[R],
                    tagConverter: Option[TagConverter[B, A, Tag1, Tag]]): Currency[R, Tag]

  def *[B: Numeric, R](factor: B)
             (implicit conv: Converter.Aux[A, B, R], R: Numeric[R]): Currency[R, Tag]

  def /[B: Numeric, R](factor: B)
                      (implicit conv: Converter.Aux[A, Double, R], R: Numeric[R]): Currency[R, Tag]

  override def hashCode: Int = {
    val prime = 31
    prime * (prime + value.toInt) + (if (value == null) 0 else value.hashCode)
  }
}

