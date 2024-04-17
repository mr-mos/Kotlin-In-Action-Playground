package chapter4_classes

import java.io.File

fun main() {

    println(
        CaseInsensitiveFileComparator.compare(
            File("/User"), File("/user")
        )
    )
    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    companionMain()
    expressionsMain()

}


///  Singleton
///////////////


object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(
            file2.path,
            ignoreCase = true
        )
    }
}

data class Person(val name: String) {                                // Such objects also have just a single instance; they don’t have a separate instance per instance of the containing class.
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int =
            p1.name.compareTo(p2.name)
    }
}


///  Companion objects
//////////////////////

class MyClass {
    companion object {
        fun callMe() {
            println("Companion object called")
        }
    }
}

class UserFactoryMethods private constructor(val nickname: String) {         // Can't be created via constructor
    companion object {                                                       // Factory pattern methods
        fun newSubscribingUser(email: String) =
            User(email.substringBefore('@'))

        fun newSocialUser(accountId: Int) =
            User(getNameFromSocialNetwork(accountId))
    }
}

fun companionMain() {
    MyClass.callMe()

    val myObject = MyClass()
    // myObject.callMe()  //  You can’t access the companion object’s members on an instance of the class; like in Java

    val subscribingUser = UserFactoryMethods.newSubscribingUser("bob@gmail.com")
    val socialUser = UserFactoryMethods.newSocialUser(4)
    println(subscribingUser.nickname)
}


interface JSONFactory<T> {
    fun fromJSON(jsonText: String): Person2?
}

class Person2(val name: String) {
    companion object : JSONFactory<Person2> {
        override fun fromJSON(jsonText: String): Person2? { /* ... */ return null
        }     // companion objects using interfaces
    }
}


///   Object expressions      -->  Anonymous objects replace Java’s use of anonymous inner classes.
////////////////////////

interface MouseListener {
    fun onEnter()
    fun onClick()
}

class ButtonAnonymous(private val listener: MouseListener) {
    var clickCount = 0
    fun wasClicked() {
        listener.onEnter()
        clickCount++
    }

}

fun expressionsMain() {
    val buttonAnonymous = ButtonAnonymous(object :
        MouseListener {         //  Declares an anonymous object implementing MouseListener; Every time an object expression is executed, a new instance of the object is created.
        override fun onEnter() {
            println("Button entered")
        }

        override fun onClick() {
            println("Button clicked")
        }
    })
    buttonAnonymous.wasClicked()
    println(buttonAnonymous.clickCount)
}

