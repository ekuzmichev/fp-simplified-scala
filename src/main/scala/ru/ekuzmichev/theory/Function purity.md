The IO monad does not make a function pure. It just makes it obvious that it’s impure.

`def foo(a: String): Int = ???` is pure function.

`def foo(bar: Bar): IO[String] = ???` is "impure" function. It can return a different value each time it is called.

Benefits of pure functions:

They’re easier to reason about

- They’re easier to combine
- They’re easier to test
- They’re easier to debug
- They’re easier to parallelize
- They are idempotent
- They offer referential transparency
- They are memoizable
- They can be lazy