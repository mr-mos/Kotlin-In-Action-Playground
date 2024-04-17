package chapter3_functions

fun myMethod(vararg listValues: Int) {
    println(listValues.sum())
}

fun main() {
    myMethod(9,8,7)

    val aList = listOf(1, 2, 3)
    myMethod(6, *aList.toIntArray())                       // spread operator

    val all = listOf(1, 2, 3).all { it > 0 }     // extension function with a closure/predicate
    println(all)

    val map = mapOf(1 to "one", 7.to("sven"), 53 to "fifty-three")    // infix form

    val (number, name) = 1 to "one"      // destructuring declaration
    val (one, two, three) = listOf(1, 2, 3)      // destructuring declaration
    println ("$number $name $one $two $three")

}

