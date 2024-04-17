package chapter1_whyKotlin

data class Person(
    val name: String,
    val age: Int? = null
)

fun main() {
    val persons = listOf(
        Person("Alice", age = 29),
        Person("Bob"),
    )
    val oldest = persons.maxBy {
        it.age ?: 0
    }
    println("The oldest is: $oldest")


    val s: String = null.toString()
    val s2 = ""

    println(s.length)
    println(s2.length)

    val test = "hallo"
    println(test)
}

// The oldest is: Person(name=Alice, age=29)