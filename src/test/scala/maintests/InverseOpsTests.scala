package maintests

import TestVals._
import conversions.Conversions._
import currencies._
import converters.TagConverter.ImplicitInstances._
import org.scalatest.flatspec.AnyFlatSpec


class InverseOpsTests extends AnyFlatSpec {
  "Inverse rubles sum" must "equals to 170.5 rubles" in {
    val inverse_add_rub =  50 ++ rd
    val formattedValue = formatDouble(inverse_add_rub.value)
    assert(Ruble(170.50) === Ruble(formattedValue))
  }

  "Reverse euro mult" must "equals to 9.8 euros" in {
    val reverse_eu_mult = 3.5 ** ed
    val formattedValue = formatDouble(reverse_eu_mult.value)
    assertResult(Euro(9.80)) { Euro(formattedValue) }
  }

  "Subtraction on existential types" must "equals to 5.13 dollars" in {
    val reverse_mult_sub = (2 ** dd).asInstanceOf[Dollar[Double]] - (rd * 2)
    val formattedValue = formatDouble(reverse_mult_sub.value)
    assert(Dollar(5.13) === Dollar(formattedValue))
  }
}