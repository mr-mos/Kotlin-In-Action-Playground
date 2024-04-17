package chapter4_classes

import java.net.URI

fun main() {
    User("mos")
    val alice = User3("Alice")
    println(alice.isSubscribed) // true
    val bob = User3("Bob", false) //
    println(bob.isSubscribed) // false

    println(PrivateUser("kodee").nickname)
    // kodee
    println(SubscribingUser("test@kotlinlang.org").nickname)
    // test
    println(SocialUser2(123).nickname)
    // kodee123
    println(SocialUser2(123).getterTestField)

    val user = User6("Alice")
    user.address = "Christoph-Rapparini-Bogen 23"
    // Address was changed for Alice:
    // "unspecified" -> "Christoph-Rapparini-Bogen 23".

}

// primary constructors
///////////////////////

class User(val nickname: String)
// same as
class User2 constructor(nickname: String) {
    val nickname: String
    init {
        this.nickname = nickname
    }
}


class User3(
    val nickname: String,
    val isSubscribed: Boolean = true  // Provides a default value for the constructor parameter
)


open class User4(val nickname: String) { /* ... */ }
class SocialUser(nickname: String) : User4(nickname) { /* ... */ }


class Secret private constructor(private val agentName: String) {} // This class has a private constructor.



// secondary constructors
/////////////////////////


open class Downloader {
    constructor(url: String?) {
        // some code
    }
    constructor(uri: URI?) {
        // some code
    }
}


//  nontrivial properties
/////////////////////////

interface User5 {
    val nickname: String
    val getterTestField: String
        get() = nickname+"whatever"                          // Property doesn’t have a backing field: the result value is computed on each access.
}

class PrivateUser(override val nickname: String) : User5     //   Primary constructor property; defines the backing field

class SubscribingUser(val email: String) : User5 {
    override val nickname: String
        get() = email.substringBefore('@')                   //   Custom getter;  calculated each time
}

class SocialUser2(val accountId: Int) : User5 {
    override val nickname = getNameFromSocialNetwork(accountId) // Property initializer; calculated just once during initialisation.
}

fun getNameFromSocialNetwork(accountId: Int) =
    "kodee$accountId"



// backing fields

class User6(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
                Address was changed for $name:
                "$field" -> "$value". 
                """.trimIndent()    // Reads the backing field value;  you use the special identifier field to access the value of the backing field.
            )
            field = value          //   Updates the backing field value with the provided string
        }
}


class LengthCounter {
    var counter: Int = 0
        private set                       //  You can’t change this property outside of the class.

    fun addWord(word: String) {
        counter += word.length
    }
}