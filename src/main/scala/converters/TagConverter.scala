package converters

import currencies._
import conversions.NumericConversions._
import conversions.ConversionConstants._
import CurrencyTag._


abstract class TagConverter[A, B, From <: CurrencyTag, To <: CurrencyTag] {
  def convert(currency: Currency[A, From]): Currency[B, To]
}

object TagConverter {
  import TagConverterTypes._

  object TagConverterTypes {
    type identityTag[A, B, Tag <: CurrencyTag] = TagConverter[A, B, Tag, Tag]
    type identityType[Type, Tag1 <: CurrencyTag, Tag2 <: CurrencyTag] = TagConverter[Type, Type, Tag1, Tag2]
    type identity[Type, Tag <: CurrencyTag] = TagConverter[Type, Type, Tag, Tag]
  }

  object ImplicitInstances {
    implicit val identityDollarTagDI: Option[identityTag[Double, Int, DollarTag.type]] = None
    implicit val identityDollarTagID: Option[identityTag[Int, Double, DollarTag.type]] = None
    implicit val identityDollarD: Option[identity[Double, DollarTag.type]] = None
    implicit val identityDollarI: Option[identity[Int, DollarTag.type]] = None

    implicit val identityEuroTagDI: Option[identityTag[Double, Int, EuroTag.type ]] = None
    implicit val identityEuroTagID: Option[identityTag[Int, Double, EuroTag.type ]] = None
    implicit val identityEuroD: Option[identity[Double, EuroTag.type ]] = None
    implicit val identityEuroI: Option[identity[Int, EuroTag.type ]] = None

    implicit val identityRubleTagDI: Option[identityTag[Double, Int, RubleTag.type]] = None
    implicit val identityRubleTagID: Option[identityTag[Int, Double, RubleTag.type]] = None
    implicit val identityRubleD: Option[identity[Double, RubleTag.type ]] = None
    implicit val identityRubleI: Option[identity[Int, RubleTag.type]] = None

    implicit val euroToDollarDI: Option[TagConverter[Double, Int, EuroTag.type, DollarTag.type]] = Some(
      currency => Dollar(currency.value * euroToDollarFactor)
    )
    implicit val euroToDollarID: Option[TagConverter[Int, Double, EuroTag.type, DollarTag.type]] = Some(
      currency => Dollar(currency.value * euroToDollarFactor)
    )
    implicit val euroToDollarDD: Option[identityType[Double, EuroTag.type, DollarTag.type]] = Some(
      currency => Dollar(currency.value * euroToDollarFactor)
    )

    implicit val dollarToEuroDI: Option[TagConverter[Double, Int, DollarTag.type, EuroTag.type]] = Some(
      currency => Euro(currency.value * dollarToEuroFactor)
    )
    implicit val dollarToEuroID: Option[TagConverter[Int, Double, DollarTag.type, EuroTag.type]] = Some(
      currency => Euro(currency.value * dollarToEuroFactor)
    )
    implicit val dollarToEuroDD: Option[identityType[Double, DollarTag.type, EuroTag.type]] = Some(
      currency => Euro(currency.value * dollarToEuroFactor)
    )

    implicit val rubToEuroDI: Option[TagConverter[Double, Int, RubleTag.type, EuroTag.type]] = Some(
      currency => Euro(currency.value * rubleToEuroFactor)
    )
    implicit val rubToEuroID: Option[TagConverter[Int, Double, RubleTag.type, EuroTag.type]] = Some(
      currency => Euro(currency.value * rubleToEuroFactor)
    )
    implicit val rubToEuroDD: Option[identityType[Double, RubleTag.type, EuroTag.type]] = Some(
      currency => Euro(currency.value * rubleToEuroFactor)
    )

    implicit val dollarToRubDI: Option[TagConverter[Double, Int, DollarTag.type, RubleTag.type]] = Some(
      currency => Ruble(currency.value * dollarToRubleFactor)
    )
    implicit val dollarToRubID: Option[TagConverter[Int, Double, DollarTag.type, RubleTag.type]] = Some(
      currency => Ruble(currency.value * dollarToRubleFactor)
    )
    implicit val dollarToRubDD: Option[identityType[Double, DollarTag.type, RubleTag.type]] = Some(
      currency => Ruble(currency.value * dollarToRubleFactor)
    )

    implicit val euroToRubDI: Option[TagConverter[Double, Int, EuroTag.type, RubleTag.type]] = Some(
      currency => Ruble(currency.value * euroToRubleFactor)
    )
    implicit val euroToRubID: Option[TagConverter[Int, Double, EuroTag.type, RubleTag.type]] = Some(
      currency => Ruble(currency.value * euroToRubleFactor)
    )
    implicit val euroToRubDD: Option[identityType[Double, EuroTag.type, RubleTag.type]] = Some(
      currency => Ruble(currency.value * euroToRubleFactor)
    )

    implicit val rubToDollarDI: Option[TagConverter[Double, Int, RubleTag.type, DollarTag.type]] = Some(
      currency => Dollar(currency.value * rubleToDollarFactor)
    )
    implicit val rubToDollarID: Option[TagConverter[Int, Double, RubleTag.type, DollarTag.type]] = Some(
      currency => Dollar(currency.value * rubleToDollarFactor)
    )
    implicit val rubToDollarDD: Option[identityType[Double, RubleTag.type, DollarTag.type]] = Some(
      currency => Dollar(currency.value * rubleToDollarFactor)
    )
  }
}