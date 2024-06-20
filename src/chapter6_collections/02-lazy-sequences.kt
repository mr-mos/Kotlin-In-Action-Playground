package chapter6_collections

import java.io.File

// Sequences give you an alternative way to perform such computations that avoids the creation of intermediate temporary objects, similar to how Java 8’s streams do.

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    val eagerly = people.map(Person::name).filter { it.startsWith("A") }
    println(eagerly)

    val lazy = people                                       //  no intermediate collections to store the elements; more efficient
        .asSequence()
        .map(Person::name)
        .filter { it.startsWith("A") }
        .toList()
    println(lazy)


    println(
        listOf(1, 2, 3, 4)
            .asSequence()                  // REMOVE it for comparing collections with sequences
            .map {
                println ("intermediate: $it")
                it * it
            }
            .find { it > 3 }
    )

    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())    //  terminal operation sum()

    val file = File("/Users/svtk/.HiddenDir/a.txt")
    println(file.isInsideHiddenDirectory())

}


fun File.isInsideHiddenDirectory() =                                    // extension function with generateSequence
    generateSequence(this) {
        println("File: ${it.name}")
        it.parentFile
    }.find { it.isHidden }