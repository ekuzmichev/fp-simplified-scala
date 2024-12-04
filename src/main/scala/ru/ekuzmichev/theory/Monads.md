### Monads

Their primary purpose is to let you compose code in for expressions (i.e., to glue code together)

For a Scala class to be a monad, it needs three things:

- A map method
- A flatMap method
- Some sort of lift function (to “lift” another type into the monad)
