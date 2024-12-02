### Drawbacks of FP

- Writing pure functions is easy, but combining them into a complete application is where things get hard.
- The advanced math terminology (monad, monoid, functor, etc.) makes FP intimidating.
- For many people, recursion doesn’t feel natural.
- Because you can’t mutate existing data, you instead use a pattern that I call, “Update as you copy.”
- Pure functions and I/O don’t really mix.
- Using only immutable values and recursion can potentially lead to performance problems, including RAM use and speed.

### Drawbacks of FP in Scala

- You can mix FP and OOP styles.
- Scala doesn’t have a standard FP library.
