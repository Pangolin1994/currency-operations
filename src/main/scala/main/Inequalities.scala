package main


object Inequalities {
  trait =!=[A, B]

  object =!= {
    implicit def neq[A, B]: A =!= B = null
    implicit def neqAmbiguous1[A]: A =!= A = null
    implicit def neqAmbiguous2[A]: A =!= A = null
  }
}