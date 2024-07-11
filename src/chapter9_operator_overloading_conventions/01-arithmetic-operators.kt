package chapter9_operator_overloading_conventions

import java.math.BigDecimal


fun main() {
    arithmeticOperations()
    compoundAssignmentOperators()
    unaryOperators()
}


data class Point(val x: Int, val y: Int) {
//    operator fun plus(other: Point): Point {                    // "operator" defines an operator function named plus
//        return Point(x + other.x, y + other.y)
//    }
}
// or as an extension method:
operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

fun arithmeticOperations() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2)

    println('a' * 5)

    println(0x0F and 0xF0)     // infix notion of an operator (defined by Kotlin)
}


////////////  compound assignment operators


// defined in the standard Kotlin library
// try not to add both plus and plusAssign operations at the same time
operator fun <T> MutableCollection<T>.plusAssign(element: T) {
    this.add(element)
}


fun compoundAssignmentOperators() {
    var point = Point(1, 2)
    point += Point(3, 4)      // point = point + Point(3, 4)
    println(point)

    val numbers = mutableListOf<Int>()
    numbers += 42
    println(numbers[0])
}


////////////  overloading unary operators


operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}

// defined in the standard Kotlin library
operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun unaryOperators() {
    val p = Point(10, 20)
    println(-p)

    var bd = BigDecimal.ZERO
    println(bd++)                            //  same semantics for pre- and post-increment operators as for the regular number
    // 0
    println(bd)
    // 1
    println(++bd)
    // 2
}
