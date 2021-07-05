package maintests

import TestVals.formatDouble
import currencies._
import conversions.Conversions._
import converters.TagConverter.ImplicitInstances._
import org.scalatest.flatspec.AnyFlatSpec


class ImplicitOpsTests extends AnyFlatSpec {
  "Rubles ops 1" must "equals to 6.80 rubles" in {
    val rub = 10.ruble * 3 / 5 + 4.ruble / 5
    val formattedValue = formatDouble(rub.value)
    assert(Ruble(6.80) === Ruble(formattedValue))
  }

  "Rubles ops 2" must "equals to 16.00 rubles" in {
    val rub = 3.2 * 5.0.ruble
    val formattedValue = formatDouble(rub.value)
    assert(Ruble(16.00) === Ruble(formattedValue))
  }

  "Rubles ops 2" must "equals to 3.72 rubles" in {
    val rub = 6.2.ruble * 3 / 5
    val formattedValue = formatDouble(rub.value)
    assert(Ruble(3.72) === Ruble(formattedValue))
  }
}