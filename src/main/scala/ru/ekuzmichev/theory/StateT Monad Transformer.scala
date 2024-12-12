package ru.ekuzmichev.theory

trait Monad[F[_]] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  def pure[A](a: A): F[A]
  def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(a => pure[B](f(a)))
}

object Monads {
  implicit val optionMonad: Monad[Option] = new Monad[Option] {
    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)
    override def pure[A](a: A): Option[A]                                   = Some(a)
  }

  implicit val ioMonad: Monad[IO] = new Monad[IO] {
    override def flatMap[A, B](fa: IO[A])(f: A => IO[B]): IO[B] = fa.flatMap(f)
    override def pure[A](a: A): IO[A]                           = IO(a)
  }
}

case class StateT[F[_], S, A](run: S => F[(S, A)]) {
  def flatMap[B](g: A => StateT[F, S, B])(implicit M: Monad[F]): StateT[F, S, B] = StateT { (s0: S) =>
    M.flatMap(run(s0)) { case (s1, a) =>
      g(a).run(s1)
    }
  }

  def map[B](f: A => B)(implicit M: Monad[F]): StateT[F, S, B] = flatMap(a => StateT.pure(f(a)))
}

object StateT {
  def pure[M[_], S, A](v: A)(implicit M: Monad[M]): StateT[M, S, A] = StateT(run = s => M.pure((s, v)))
}
