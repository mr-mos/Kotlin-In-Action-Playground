package chapter8_types_collections_arrays


// -- At run time, the number types are represented in the most efficient way possible. In most cases—for variables, properties, parameters, and return types—Kotlin’s Int type is compiled to the Java primitive type int

fun main() {
    val positiveNumber: UInt = 145u
    showProgress(positiveNumber)
    val i = 1
    val lo: Long = i.toLong()           // Kotlin doesn’t automatically convert numbers from one type to another
    // Integer.valueOf(42).equals(Long.valueOf(42)) --> False in Java
    println(42L == 42.toLong())        // Kotlin requires you to convert the types explicitly so that only values of the same type are compared
    // conversion is applied automatically if you use a number literal to initialize a variable of a known type or pass it as an argument to a function
    val b: Byte = 1
    val l = b + 1L
    println(l::class)
    println(l::class.java)
    printALong(42)
    // overflow like in Java
    println(Int.MAX_VALUE + 1)
    println(Int.MIN_VALUE - 1)
    // convertions
    println("seven".toIntOrNull())
    if ("true".toBoolean()) {                                   // For exact matches on the strings "true" and "false" during conversion, use the toBooleanStrict
        println("it is true. Everything else would be false.")
    }
    // Any and Any?: The root of the Kotlin type hierarchy    (Java: Object)
    val answer: Any = Any()
    println("Kotlin:${answer::class}  JAVA: ${answer::class.java}" )    // --> Object Instance
    val answer2: Any = 42
    println("Kotlin:${answer2::class}  JAVA: ${answer2::class.java}" )
    // The Unit type: Kotlin’s void
    // Unit is a full-fledged type, and unlike void, it can be used as a type argument. --> See Processor below

    // The Nothing type: “This function never returns”
    fail("Error occurred")


}

fun printALong(l: Long) = println(l)

fun showProgress(progress: UInt) {
    val percent = progress.coerceIn(0u, 100u)
    println("We're $percent % done!")
}

fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

interface Processor<T> {
    fun process(): T
}
class NoResultProcessor : Processor<Unit> {
    override fun process() {
        // do stuff
    }
}