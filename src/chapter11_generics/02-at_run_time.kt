package chapter11_generics


fun main() {
    // Java's and Kotlin’s generics are erased at run time

    val test: List<Int> = listOf(1, 2, 3, 4, 5)
    if (test is List<*>) {                          // similar to Java's List<?>
        println("is a List")
    }


    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())                              //  reified type parameters   -->  the type argument is known at run time

    println(isA<String>("abc"))
    println(isA<String>(123))


}

inline fun <reified T> isA(value: Any) = value is T   // inline function could keep (reified) the type
//  fun <T> isA(value: Any) = value is T       <--- compiler error:  Kotlin: Cannot check for instance of erased type: T


fun printSum(c: Collection<*>) {
    val intList = c as? List<Int>                               // Can't be checked at runtime listOf("a","b") would throw an exception
        ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}