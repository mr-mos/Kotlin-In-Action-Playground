package chapter2_basics

fun main() {
    println(max2(24, 18))
}

fun max(a: Int, b: Int): Int {                 // block-body
    return if (a > b) a else b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b    // expression-body; return type is optional; but a good choice for a public API


// In IntelliJ you can convert simple functions between block- and expression body by using "Alt-Enter"