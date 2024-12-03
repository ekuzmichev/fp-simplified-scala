EOP = Expression-Oriented Programming

Every line of code returns a result (“evaluates to a result”), and is therefore an expression rather than a statement.

Statements do not return results and are executed solely for their side effects.

An expression has the form:

```scala
val resultingValue = somePureFunction(someImmutableValues)
```

Statements:

```scala
order.calculateTaxes()
order.updatePrices()
```

In FP and EOP you write those same statements as expressions, like this:

```scala
val tax = calculateTax(order)
val price = calculatePrice(order)
```

When every line of code has a return value, it is said that you are writing expressions, and using an EOP style. In
contrast, statements are lines of code that do not have return values, and are executed for their side effects. When see
statements in code, you should think, “This is imperative code, not FP code.”