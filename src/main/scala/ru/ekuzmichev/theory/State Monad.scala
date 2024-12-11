package ru.ekuzmichev.theory

case class State[S, A](run: S => (S, A)) {
  def flatMap[B](g: A => State[S, B]): State[S, B] = State { (s1: S) =>
    val (s2, a) = run(s1)
    g(a).run(s2)
  }

  def map[B](f: A => B): State[S, B] = flatMap(a => State.lift(f(a)))
}

object State {
  def lift[S, A](v: A): State[S, A] = State(run = s => (s, v))
}

object StateMonadExample extends App {
  case class GolfState(distance: Int)

  private def swing(distance: Int): State[GolfState, Int] = State { (s: GolfState) =>
    val newAmount = s.distance + distance
    (GolfState(newAmount), newAmount)
  }

  private val stateWithNewDistance: State[GolfState, Int] = for {
    _             <- swing(20)
    _             <- swing(15)
    totalDistance <- swing(0)
  } yield totalDistance

  // initialize a `GolfState`
  private val initialState = GolfState(0)

  // run/execute the effect.
  // `run` is like `unsafeRunSync` in the Cats `IO` monad.
  val result: (GolfState, Int) = stateWithNewDistance.run(initialState)

  println(s"GolfState: ${result._1}")
}
