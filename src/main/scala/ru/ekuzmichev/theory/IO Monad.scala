package ru.ekuzmichev.theory

class IO[A] private (constructorCodeBlock: => A) {

  def run: A = constructorCodeBlock

  def flatMapOrig[B](f: A => IO[B]): IO[B] = IO(f(run).run)

  def flatMap[B](customAlgorithm: A => IO[B]): IO[B] = {
    val result1: IO[B] = customAlgorithm(run)
    val result2: B     = result1.run
    IO(result2)
  }

  def map[B](f: A => B): IO[B] = flatMap(a => IO(f(a)))

}

object IO {
  def apply[A](a: => A): IO[A] = new IO(a)
}

object Console {
  def getLine: IO[String]           = IO(scala.io.StdIn.readLine())
  def putStrLn(s: String): IO[Unit] = IO(println(s))
}

object IOExample extends App {
  import Console._

  for {
    _         <- putStrLn("First name?")
    firstName <- getLine
    _         <- putStrLn(s"Last name?")
    lastName  <- getLine
    _         <- putStrLn(s"First: $firstName, Last: $lastName")
  } yield ()
}

object FPRecursiveLoop extends App {
  import Console._

  private def loop: IO[Unit] = for {
    _     <- putStrLn("Type something: ")
    input <- getLine
    _     <- putStrLn(s"You said '$input'.")
    _     <- if (input == "quit") IO(()) else loop // RECURSE
  } yield ()

  loop.run
}
