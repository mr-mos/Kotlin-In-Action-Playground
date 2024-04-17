package chapter2_basics

import chapter2_basics.Color.*

enum class Color(                        // This is a rare case when a Kotlin declaration uses more keywords than the corresponding Java one: enum class versus just enum in Java.
    val r: Int,
    val g: Int,
    val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(
        238,
        130,
        238
    );                           //   Note that this example shows the only place in the Kotlin syntax where you’re required to use semicolons: if you define any methods in the enum class, the semicolon separates the enum constant list from the method definitions.

    val rgb = (r * 256 + g) * 256 + b
    fun printColor() = println("$this is $rgb")
}


fun getMnemonic(color: Color) =
    when (color) { //
        RED -> "Richard"
        ORANGE -> "Of"
        YELLOW -> "York"
        GREEN -> "Gave"
        BLUE -> "Battle"
        INDIGO -> "In"
        VIOLET -> "Vain"
    }

fun measureColor() = ORANGE
// as a stand-in for more complex measurement logic

fun getWarmthFromSensor(): String {
    return when (val color = measureColor()) {
        RED, ORANGE, YELLOW -> "warm (red = ${color.r})"
        GREEN -> "neutral (green = ${color.g})"
        BLUE, INDIGO, VIOLET -> "cold (blue = ${color.b})"
    }
}

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) { //
        setOf(RED, YELLOW), setOf(YELLOW, VIOLET) -> ORANGE //
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color") //
    }

fun boolWhen(c: Color): String =
    when {                            // like an if if else ....
        (c == RED) -> "oh rot"
        else -> "wad anderes"
    }


interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expr")
    }



///////////////////////////////////

fun main() {
    println(BLUE.rgb)
    // 255
    GREEN.printColor()
    // GREEN is 65280
    println(getMnemonic(BLUE))
    // Battle
    println(getWarmthFromSensor())
    // warm (red = 255)
    println(mix(BLUE, YELLOW))
    // GREEN
    println(boolWhen(RED))
    // oh rot
    println(eval(Sum(Num(1), Num(2))))
    // 3
}


