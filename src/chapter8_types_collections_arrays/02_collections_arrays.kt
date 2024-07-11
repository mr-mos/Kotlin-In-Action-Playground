package chapter8_types_collections_arrays

import java.util.Collections

fun main() {

    // Collections of nullable values and nullable collections
    var nullableList: List<Int?>? = listOf(1, 2, null, 4)
    nullableList = null
    var nullableMap: Map<String, Int?> = mapOf("a" to 1, "b" to null, "c" to 3)
    // nullableMap = null
    println(readNumbers2("""
        11
        zwei
        5555
    """.trimIndent()))


    // Mutable and immutable collection interfaces
//    var source: Collection<Int> = Collections.unmodifiableList((listOf(3, 5, 7)))
    var source: Collection<Int> = listOf(3, 5, 7)
    source.size
    val target: MutableCollection<Int> = arrayListOf(1)
    target.add(12)
    source = target
    println(source)

    // CAUTION:  Because Java doesn’t distinguish between read-only and mutable collections,
    // Java code can modify the collection, even if it’s declared as a read-only Collection on the Kotlin side.
    // --> can use "Collections.unmodifiableList" to make it really read-only in Java


    // Arrays  (don't use them in general)
    val letters = Array<String>(26) { i -> ('a' + i).toString() }
    println(letters.joinToString(""))
    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))
    // primitive type arrays
    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)
}


fun readNumbers2(text: String): List<Int?> = text.lineSequence().map { it.toIntOrNull() }.toList()