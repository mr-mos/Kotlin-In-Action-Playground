package chapter5_lambdas


fun alphabetWith(): String {
    return with(StringBuilder()) {                 // can use own function myWith; use "it" instead of "this"
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nNow I know the alphabet!")     // "this" is optional
        this.toString()                           // if you like to reference the object the method is defined in "this@OuterClass.toString()"
    }
}

fun myWith(myObject: StringBuilder, lamda: (StringBuilder) -> String) : String  {
    return lamda(myObject)
}

// The apply function works almost exactly the same as with ; the main difference is that apply always returns the object passed to it as an argument (in other words, the receiver object)
// this is useful is when you’re creating an instance of an object and need to initialize some properties right away

fun alphabetApply() : String {
    return StringBuilder().apply {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!")
    }.toString()
}

// The buildString function is an elegant solution for the task of creating a String with the help of StringBuilder.
// There on also collection builder functions like buildList, buildSet, and buildMap to create collections

fun alphabetBuildString() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}


// The main difference is that within the lambda of also, you access the receiver object as an argument—either by giving it a name, or using the default name it

fun alphabetAlso() : String {
    return StringBuilder().also {
        for (letter in 'A'..'Z') {
            it.append(letter)
        }
        it.append("\nNow I know the alphabet!")
    }.toString()
}


fun main() {
    println(alphabetWith())
    println(alphabetApply())
    println(alphabetBuildString())
    println(alphabetAlso())

}