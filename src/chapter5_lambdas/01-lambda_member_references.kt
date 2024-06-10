package chapter5_lambdas

fun main() {


    val lamda = { name: String ->
        println("Hello, $name!")
    }
    lamda("mos")


    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxByOrNull { it.age })       // Finds the maximum by comparing the ages using a lampda
    println(people.maxByOrNull(Person::age))     // same; delegating to a function or a property; called member reference; same as { person: Person -> person.age }

    val joined = people.joinToString(" ") { p -> p.name }   // Passing a lambda outside of parentheses
    println(joined)


    val myFavoriteNumber = run {                    // instead of "run" usage of () at the end is possible; but () has runtime overhead
        println("I'm thinking!")
        println("I'm doing some more work...")
        42
    }
    println ("myFavoriteNumber: "+myFavoriteNumber)


    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y...")
        x + y
    }
    println(sum(1, 2))


    var counter = 0
    val inc = { counter++ }    // Modify captured variables in the lambda;  not allowed in Java
    inc()
    println (counter)

    run(::salute)                  //  member reference to a top level function (isn’t a member of a class; as Person::age),
    val lamdaRef = ::saluteWithParams
    lamdaRef("Mr. Test")

    val createPerson = ::Person   // postpone the action of creating an instance of a class using a constructor reference
    val p = createPerson("Alice", 29)
    println(p)


    // Bound callable references
    val seb = Person("Sebastian", 26)
    val personsAgeFunction = Person::age   //  A member reference that returns the age of a given person
    println(personsAgeFunction(seb))
    val sebsAgeFunction = seb::age    //  A bound member reference; same as { seb.age }
    println(sebsAgeFunction())
}


data class Person(val name: String, val age: Int)

fun salute() = println("Salute!")

fun saluteWithParams(name: String) = println("Salute $name!")