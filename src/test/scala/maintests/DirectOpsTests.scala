package maintests

import TestVals._
import currencies.{Dollar, Euro, Ruble}
import converters.TagConverter.ImplicitInstances._
import org.scalatest.flatspec.AnyFlatSpec


class DirectOpsTests extends AnyFlatSpec {
  "Dollars sum" must "equals to 6.2 dollars" in {
    val dollar_sum = di + dd
    assert(Dollar(6.2) === dollar_sum)
  }

  "Euro - dollar subtraction" must "equals to 1.16 euros" in {
    val eu_dollar_sub = ed - di
    val formattedValue = formatDouble(eu_dollar_sub.value)
    assert(Euro(1.16) === Euro(formattedValue))
  }

  "Dollar multiplication" must "equals to 4.8 dollars" in {
    assertResult(Dollar(4.8)) { di * 2.4 }
  }

  "Doubled dollars plus euros" must "equals to 11.82 dollars" in {
    val doubled_dd_plus_ed = dd * 2 + ed
    val formattedValue = formatDouble(doubled_dd_plus_ed.value)
    assert(Dollar(11.82) === Dollar(formattedValue))
  }

  "Ruble division/mult minus dollar" must "equals to 92.04 rubles" in {
    val divided_rd_minus_dd = rd / 1.5 * 5 - dd
    val formattedValue = formatDouble(divided_rd_minus_dd.value)
    assertResult(Ruble(92.04)) { Ruble(formattedValue) }
  }

  "Euro multiplication plus dollar" must "equals to 14.64 euros" in {
    val mult_ed_plus_dd = ed * 4 + dd
    val formattedValue = formatDouble(mult_ed_plus_dd.value)
    assertResult(Euro(14.64)) { Euro(formattedValue) }
  }

  "Ruble - dollar sum" must "equals to 267.94 rubles" in {
    val rub_dol_sum = rd + di
    val formattedValue = formatDouble(rub_dol_sum.value)
    assert(Ruble(267.94) === Ruble(formattedValue))
  }

  "Ruble - dollar subtraction" must "equals to 86.19 rubles" in {
    val rub_dol_mult_div = rd * 2 - dd / 2
    val formattedValue = formatDouble(rub_dol_mult_div.value)
    assertResult(Ruble(86.19)) { Ruble(formattedValue) }
  }
}
