package main

import converters.TagConverter.ImplicitInstances._
import conversions.Conversions._


object Main {
  def main(argv: Array[String]): Unit = {
    val rub = 10.ruble * 3 / 5 + 4.ruble / 5
    println(rub)

    val rub1 = 3 * 5.0.ruble
    println(rub1)

    val rub2 = 6.2.ruble * 3 / 5
    println(rub2)
  }
}