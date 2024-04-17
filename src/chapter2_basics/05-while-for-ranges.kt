package chapter2_basics

fun fizzBuzz(i: Int) = when {               // the better switch/case (incl. pattern matching)
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
} + " "


fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'


fun main() {

    for (i in 100 downTo 1 step 2) {                       //    in 1..100  or  1..<100 (same as 1..99)
        print(fizzBuzz(i))
    }
    // 1 2 Fizz 4 Buzz Fizz 7 ...

    println()

    val collection = listOf("red", "green", "blue")
    for(color in collection) {
        print("$color ")
    }
    // red green blue

    println()

    val binaryReps = mutableMapOf<Char, String>()
    for (char in 'A'..'F') {
        val binary = char.code.toString(radix = 2)
        binaryReps[char] = binary
    }
    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
    // A = 1000001    D = 1000100
    // B = 1000010    E = 1000101
    // C = 1000011    F = 1000110
    // (output split into columns for conciseness)

    println()

    val list = listOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
    // 0: 10
    // 1: 11
    // 2: 1001

    println(isLetter('q'))
    // true
    println(isNotDigit('5'))
    // false
}