package ru.ekuzmichev.theory

object FunctionBindingStraightforwardExample extends App {
  def f(a: Int): (Int, String) = (a * 2, "[f result]")
  def g(a: Int): (Int, String) = (a * 3, "[g result]")
  def h(a: Int): (Int, String) = (a * 4, "[h result]")

  def bind(fn: Int => (Int, String), res: (Int, String)): (Int, String) = res match {
    case (i, s) =>
      fn(i) match {
        case (ii, ss) => (ii, s + ss)
      }
  }

  val fResult = f(100)
  val gResult = bind(g, fResult)
  val hResult = bind(h, gResult)

  println(hResult)
}

object WrapperExample extends App {
  case class Wrapper[A](a: A) {
    def flatMap[B](f: A => Wrapper[B]): Wrapper[B] = f(a)
    def map[B](f: A => B): Wrapper[B]              = new Wrapper[B](f(a))
  }

  val result: Wrapper[Int] = for {
    a <- Wrapper(1)
    b <- Wrapper(2)
    c <- Wrapper(3)
  } yield a + b + c

  println(result.a)
}

// For something to be used in a for expression, it doesn’t have to be a class that implements
// the map and flatMap methods (like Sequence); it just needs to return a type that implements
// map and flatMap methods.

object FunctionBindingForLoopExample extends App {
  case class Debuggable[A](value: A, message: String) {
    def map[B](f: A => B): Debuggable[B] = Debuggable(f(value), message)

    def flatMap[B](f: A => Debuggable[B]): Debuggable[B] = f(value) match {
      case Debuggable(value, message) => Debuggable(value, this.message + message)
    }
  }

  def f(a: Int): Debuggable[Int] = Debuggable(a * 2, "[f result]")
  def g(a: Int): Debuggable[Int] = Debuggable(a * 3, "[g result]")
  def h(a: Int): Debuggable[Int] = Debuggable(a * 4, "[h result]")

  val result = for {
    fResult <- f(100)
    gResult <- g(fResult)
    hResult <- h(gResult)
  } yield hResult

  println(result)
}
