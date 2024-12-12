### Monad transformers

A **monad transformer** is a special version of a monad that can stack its own effects on those of another monad. If you
stack a monad transformer on another monad, the result forms a monad, which combines the effects of both monads
together.

Not all monads have monad transformers. For example, the IO monad doesn’t have a transformer version. Fortunately, we’re
in luck: while IO doesn’t come in a transformer flavor, the State monad does.