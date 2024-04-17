package chapter4_classes



// inline classes: They allow you to introduce a layer of type-safety without compromising performance.
// No need for  large number of short-lived objects need to be allocated and subsequently garbage-collected.


interface PrettyPrintable {
    fun prettyPrint()
}


// To qualify as "inline", your class must have exactly one property, which needs to be initialized in the primary constructor. Inline classes also don’t participate in class hierarchies: They don’t extend other classes, and can’t be extended themselves.
// However, they can still implement interfaces, define methods, or provide computed properties

@JvmInline
value class UsdCent(val amount: Int): PrettyPrintable {
    val salesTax get() = amount * 0.06
    override fun prettyPrint() = println("${amount}¢")
}

fun main() {
    val expense = UsdCent(1_99)
    println(expense.salesTax)
    expense.prettyPrint()
}
