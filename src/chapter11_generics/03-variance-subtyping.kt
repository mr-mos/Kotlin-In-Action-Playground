package chapter11_generics


typealias NameCombiner = (String, String, String, String) -> String
typealias TestAlias = String

fun main() {

    fun printContents(s: TestAlias) {
        println(s)
    }
    println("test")

    fun printContents(list: List<Any>) {
        println(list.joinToString())
    }
    printContents(listOf("abc", "bac"))

    val strings = mutableListOf("abc", "bac")
    // Not allowed:  addAnswer(strings)
    println(strings.maxBy { it.length })


    val authorsCombiner: NameCombiner = { a, b, c, d -> "$a et al." }
    val bandCombiner: NameCombiner = { a, b, c, d -> "$a, $b & The Gang" }

    fun combineAuthors(combiner: NameCombiner) {
        println(combiner("Sveta", "Seb", "Dima", "Roman"))
    }


}


interface Producer<out T> {               // "out" marks the type parameter as covariant (instead of invariant);  Producer<A> is a subtype of Producer<B> if A is a subtype of B. For example List<Number> and List<Int> but not List<Any> and List<Int>
    fun produce(): T
}

// The read-only interface List in Kotlin is declared as covariant, which means List<String> is a subtype of List<Any>.
// The function interface is declared as contravariant on its first type parameter and covariant on its second, which makes (Animal) -> Int a subtype of (Cat) -> Number.


interface Function1<in P, out R> {       // covariant on one type parameter and contravariant on another
    operator fun invoke(p: P): R
}

fun addAnswer(list: MutableList<Any>) {                // it’s not safe to pass a MutableList<String> as an argument when a MutableList<Any> is expected; the Kotlin compiler correctly forbids that.
    list.add(42)
}