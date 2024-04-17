package chapter23

class Person(val name: String) {            // prop in constructor
    val other: String? = null             // other prop
    val isShort: Boolean                    // property with an expression body:  val isShort get() = name.length < 10
        get() {
            return name.length < 10
        }

    fun isShort2(): Boolean {              // function
        return name.length < 10
    }

//    You might ask whether it’s better to declare a property with a custom getter, or define a function inside the class (referred to as a member function or method in Kotlin). Both options are similar: There is no difference in implementation or performance; they only differ in readability. Generally, if you describe the characteristic (the property) of a class, you should declare it as a property. If you are describing the behavior of a class, choose a member function instead.
}

fun main() {
    val person = Person("mos")
    println("Hello: ${person.name}. ${if (person.isShort) "A short name!" else "The name is long!"}    ${person.other}")
}