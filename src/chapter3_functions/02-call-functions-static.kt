package chapter3_functions

var topLevelProperty = "test"     // stored in a private static Java field with getter and setter methods
const val topLevelProperty2 = "test2"   // stored as a public static final field

fun someArgs(one: String, two: String, three: String) {    // top-level function; could be called in Java as a static method via ClassNameAsFileNameKT.someArgs
    println("$one  $two $three")
}

fun main() {
    println(someArgs("eins", "zwei", "drei"))
    println(someArgs("eins", three="333", two="222"))          // named arguments
    println(topLevelProperty)
    topLevelProperty = "neu"
    println(topLevelProperty)
    println(topLevelProperty2)
}