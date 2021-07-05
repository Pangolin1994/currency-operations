package conversions

import currencies.{Currency, Dollar, Euro, Ruble, CurrencyTag}
import ConversionConstants._
import converters.Converter
import scala.math.Numeric
import scala.math.Numeric.Implicits._


object Conversions {
  type AnyTagCurrency[R] = Currency[R, _ <: CurrencyTag]

  def getCurrency[B, Tag <: CurrencyTag, R](currency: Currency[B, Tag], value: R)
                            (implicit R: Numeric[R]): AnyTagCurrency[R] =
    currency.tag match {
      case CurrencyTag.RubleTag => Ruble(value)
      case CurrencyTag.EuroTag => Euro(value)
      case CurrencyTag.DollarTag => Dollar(value)
    }

  implicit class NumericToCurrency[T: Numeric](val value: T) {
    def dollar: Dollar[T] = Dollar(value)

    def euro: Euro[T] = Euro(value)

    def ruble: Ruble[T] = Ruble(value)

    def ++[B: Numeric, R, Tag <: CurrencyTag](currency: Currency[B, Tag])
                                             (implicit conv: Converter.Aux[T, B, R], R: Numeric[R]): AnyTagCurrency[R] = {
      val resultValue = conv.fromA(value) + conv.fromB(currency.value)
      getCurrency(currency, resultValue)
    }

    def --[B: Numeric, R, Tag <: CurrencyTag](currency: Currency[B, Tag])
                                             (implicit conv: Converter.Aux[T, B, R], R: Numeric[R]): AnyTagCurrency[R] = {
      val resultValue = conv.fromA(value) - conv.fromB(currency.value)
      getCurrency(currency, resultValue)
    }

    def **[B: Numeric, R, Tag <: CurrencyTag](currency: Currency[B, Tag])
                                             (implicit conv: Converter.Aux[T, B, R], R: Numeric[R]): AnyTagCurrency[R] = {
      val resultValue = conv.fromA(value) * conv.fromB(currency.value)
      getCurrency(currency, resultValue)
    }
  }

  implicit class ToRuble(val value: Double) {
    def *[B: Numeric, R, Tag <: CurrencyTag](currency: Currency[B, Tag])
                                            (implicit conv: Converter.Aux[Double, B, R], R: Numeric[R]): AnyTagCurrency[R] = {
      val resultValue = conv.fromA(value) * conv.fromB(currency.value)
      getCurrency(currency, resultValue)
    }
  }

  implicit class DollarConversions[T: Numeric](dollar: Dollar[T]) {
    def euro: Euro[Double] = Euro(dollar.value.toDouble * dollarToEuroFactor)

    def ruble: Ruble[Double] = Ruble(dollar.value.toDouble * dollarToRubleFactor)
  }

  implicit class EuroConversions[T: Numeric](euro: Euro[T]) {
    def dollar: Dollar[Double] = Dollar(euro.value.toDouble * euroToDollarFactor)

    def ruble: Ruble[Double] = Ruble(euro.value.toDouble * euroToRubleFactor)
  }

  implicit class RubleConversions[T: Numeric](ruble: Ruble[T]) {
    def euro: Euro[Double] = Euro(ruble.value.toDouble * rubleToEuroFactor)

    def dollar: Dollar[Double] = Dollar(ruble.value.toDouble * rubleToDollarFactor)
  }
}