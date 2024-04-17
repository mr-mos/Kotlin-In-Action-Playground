package chapter2_basics

fun main() {

    val explict : String = "test1"
    val inferredType = "test2"
    val fix : String
    fix = "test3"
    // no fix = "test_wrong"
    var changeable : String = "test4"
    changeable = "test5"

    println ("Test: $explict, $inferredType, $fix, $changeable")

    println ("What's your name?")

    val input = readln()
    val name = if (input.isNotBlank()) input else "Kotlin"
    println("Hello, $name!") //
}