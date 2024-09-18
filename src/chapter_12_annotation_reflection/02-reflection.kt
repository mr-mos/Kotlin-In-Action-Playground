package chapter_12_annotation_reflection

import kotlin.reflect.full.*
import kotlin.reflect.full.memberProperties


class Person(val name: String, val age: Int)

fun foo(x: Int) = println(x)

fun main() {
    val person = Person("Alice", 29)
    val kClass = person::class
    println(kClass.simpleName)
    kClass.memberProperties.forEach { println(it.name) }

    val kFunction = ::foo
    kFunction.call(42)
    kFunction.invoke(42)     // prevents you from accidentally passing an incorrect number of arguments to the function
}