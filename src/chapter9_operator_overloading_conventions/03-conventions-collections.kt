package chapter9_operator_overloading_conventions

import java.time.LocalDate


operator fun Point.get(index: Int): Int {                           // implements the  indexed access operator a[b]
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

operator fun MosClass.set(index: Int, value: String) {
    this.value = "$value $index"
}


operator fun Point.get(index1: Int, index2: Int): String {
    return "two parameters $index1 and $index2"
}


data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x..<lowerRight.x &&
            p.y in upperLeft.y..<lowerRight.y
}

operator fun MosClass.rangeTo(index: Int) : ClosedRange<Int> {
    return IntRange(0, index - 1)
}

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object : Iterator<LocalDate> {
        var current = start

        override fun hasNext() =
            current <= endInclusive

        override fun next(): LocalDate {
            val thisDate = current
            current = current.plusDays(1)
            return thisDate
        }
    }


fun main() {
    // get-index-access operator
    val p = Point(10, 20)
    println(p[1])
    println(p[0])
    println(p[0, 4])

    // set-index-access operator
    val mosClass = MosClass("test")
    mosClass[1] = "index setter for a arbitrary class"
    println(mosClass.value)

    // in operator
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20, 30) in rect)
    println(Point(5, 5) in rect)

    // rangeTo
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println("Has vacation: ${now.plusWeeks(1) in vacation}")

    // iterator
    for (c in "abc") println (c)
    val newYear = LocalDate.ofYearDay(2042, 1)
    val daysOff = newYear.minusDays(1)..newYear
    for (dayOff in daysOff) println (dayOff)

}
