### IO Monad

IO monad does not make I/O pure

- When you use IO monad, your I/O function signatures will declare something like `IO[A]`. This screams to other
  developers, “Watch out: this function interacts with the outside world!”
- IO monad lets you use I/O functions in Scala for expressions.
- If a function signature shows that it returns `IO[Unit]`, developers know that the function writes data to the outside
  world. (The function presumably takes one or more input parameters that will be written to the outside world.)


