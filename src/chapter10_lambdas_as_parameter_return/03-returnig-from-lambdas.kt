package chapter10_lambdas_as_parameter_return


// If you use the return keyword in a lambda, it returns from the function in which you called the lambda, not just from the lambda itself. Such a return statement is called a non-local return
// Note that the return from the outer function is possible only if the function that takes the lambda as an argument is inlined

fun main() {
    lookForAliceReturingFromLambdaNotFromFunction(people)
    lookForAliceWithAnonymousFun(people)
}


data class Person(val name: String, val age: Int)

val people = listOf(Person("Mos", 50), Person("Alice", 29), Person("Bob", 31), Person("Alice", 44))


fun lookForAliceReturingFromLambdaNotFromFunction(people: List<Person>) {
//    people.forEach label@{
//        if (it.name != "Alice") return@label
//        println("Found Alice!")
//    }
    people.forEach {
        if (it.name != "Alice") return@forEach
        println("Found Alice!")
    }
}

// Anonymous functions follow the same rules as regular functions for specifying the return type; it’s another syntactic form of a lambda expression; help shorten code that has a lot of early return statements

fun lookForAliceWithAnonymousFun(people: List<Person>) {
    people.forEach(fun (person) {
        if (person.name == "Alice") return
        println("${person.name} is not Alice")
    })
    val young = people.filter(fun (person): Boolean {
        return person.age < 30
    })
    println(young)
    val youngExpressionBody = people.filter(fun (person) =  person.age < 40)
    println(youngExpressionBody)
    val youngRegularLambda = people.filter { it.age < 50 }
    println(youngRegularLambda)
}