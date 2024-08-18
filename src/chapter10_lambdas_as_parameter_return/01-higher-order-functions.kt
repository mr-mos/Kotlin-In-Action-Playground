package chapter10_lambdas_as_parameter_return

// A higher-order function is a function that takes another function as an argument or returns one.

fun main() {

    // function types
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    val action: () -> Unit = { println(42) }
    action()
    var canReturnNull: (Int, Int) -> Int? = { x, y -> null }
    var funOrNull: ((Int, Int) -> Int)? = null

    // as parameter
    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }
    twoAndThree()   // uses the default parameter
    println("ab343%jd".filter { it in 'a'..'z' })

    // as return value
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    println("Shipping costs ${calculator(Order(3))}")

}


fun twoAndThree(operation: (Int, Int) -> Int = { a,b -> a - b }) {    // or with names for lambda parameter:   fun twoAndThree(operation: (arg1name: Int,arg2name: Int) -> Int) {
    val result = operation(2, 3)      // same as operation.invoke(2, 3)
    println("The result is $result")
}

fun String.filter(predicate: (Char) -> Boolean): String {
    return buildString {
        for (char in this@filter) {
            if (predicate(char)) append(char)
        }
    }
}


enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount }
    }

    return { order -> 1.2 * order.itemCount }
}
