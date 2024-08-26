package chapter11_generics

fun main() {

    val authors = listOf("Sveta", "Seb", "Dima", "Roman")                // List of Strings; inferred by the Kotlin compiler

    val readers: MutableList<String> = mutableListOf("Seb", "Hadi")
    val readers2 = mutableListOf<String>("Seb", "Hadi")                         // equivalent to the one above


    val letters = ('a'..'z').toList()
    println(letters.slice<Char>(0..2))                                        // uses function’s type parameter T; mostly the compiler infers the type; no need to specify it
    println(readers.filter { it !in authors })

    genericInExtensionProperties()
    genericClassInterfaceDefinition()
    genericTypeRestriction()
}


fun genericInExtensionProperties() {
    println(listOf(1,2,3,4,5).penultimate)
    println(listOf(1,2,3,4,5).penultimateNoGeneric)
}

val <T> List<T>.penultimate: T
    get() = this[size - 2]

val  List<Any>.penultimateNoGeneric: Any
    get() = this[size - 2]




fun genericClassInterfaceDefinition() {
    println(MyString().compareTo("something"))
    println(MyOtherType<String?>().compareTo(null))                   // nullable would be possible
    println(MyOtherTypeNotNull1<String>().compareTo("not null"))      // disallow null (String?) for the generic Type by using the upper bound "Any"
}

interface MyComparable<T> {
    fun compareTo(other: T): Int
    // fun compareTo(other: T & Any): Int                       // Would define that parameter T couldn't be null
}

class MyString : MyComparable<String> {
    override fun compareTo(other: String): Int = 5
}

class MyOtherType<X> : MyComparable<X> {
    override fun compareTo(other: X): Int = 5
}

class MyOtherTypeNotNull1<X : Any> : MyComparable<X> {
    override fun compareTo(other: X): Int = 5
}




fun genericTypeRestriction() {
    println(oneHalf(3))
    println(max("kotlin", "java"))
    val helloWorld = StringBuilder("Hello World")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld)
}

fun <T : Number> oneHalf(value: T): Double {
    return value.toDouble() / 2.0
}

fun <T: Comparable<T>> max(first: T, second: T): T {                // one constraint
    return if (first > second) first else second
}

fun <T> ensureTrailingPeriod(seq: T)  where T : CharSequence, T : Appendable {   // multiple constraints on generics
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}