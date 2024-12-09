### IO Monad

IO monad does not make I/O pure

- When you use IO monad, your I/O function signatures will declare something like `IO[A]`. This screams to other
  developers, “Watch out: this function interacts with the outside world!”
- IO monad lets you use I/O functions in Scala for expressions.
- If a function signature shows that it returns `IO[Unit]`, developers know that the function writes data to the outside
  world. (The function presumably takes one or more input parameters that will be written to the outside world.)

#### History of IO

- In Haskell, you only write pure functions.
- You can compose your functions just like algebraic equations to create software applications.
- But when the Haskell creators got to I/O, they realized they had a problem.
- I/O functions aren’t referentially transparent (RT), so you can’t combine them like algebraic expressions.
  And if you can’t compose I/O functions with your pure functions, you can’t write an application that interacts with
  the outside world.

The IO monad contains a bit of a “trick” that lets you use I/O functions as though they are RT.
The trick lets you write I/O functions as effects. You use IO to describe how an I/O function works.
Instead of dealing with the result of the I/O function at the time, when the I/O function appears to be called, you
defer the actual action until some time later when you really want it to be triggered.

IO lets you write code that contains side effects in a compositional style. In short, if you want to write for
expressions using pure functions and I/O functions in a compositional style, the IO monad provides a solution.
