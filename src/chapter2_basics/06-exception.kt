package chapter2_basics

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.StringReader


fun readNumber(reader: BufferedReader) {
    val number = try {                   // exception as an expression
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null    // return
    }
}

fun main() {

    val reader = BufferedReader(StringReader("not a number"))
    println (readNumber(reader))

    // throw is an expression;  don't use "new" if you create instances
    val number = -1
    val percentage =
        if (number in 0..100)
            number
        else
            throw FileNotFoundException (             // checked exceptions are "unchecked" in KotlinS
                "A percentage value must be between 0 and 100: $number"
            )

}
