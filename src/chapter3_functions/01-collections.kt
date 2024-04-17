package chapter3_functions


fun main() {

    val set = setOf(1, 7, 5, 3)
    val setMutable = mutableSetOf(1, 7, 5, "3")
    val list = listOf(1, 7, 5, 3)
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    println ("${set.javaClass} $set")
    println ("${list.javaClass} $list   Last: ${list.last()}  Shuffle: ${list.shuffled()}  Sum:${list.sum()}")
    println ("${map.javaClass} $map")
    setMutable.add("11")
    println ("Mutable ${setMutable.javaClass} $setMutable")

}