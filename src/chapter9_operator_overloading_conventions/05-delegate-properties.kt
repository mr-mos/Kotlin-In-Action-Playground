package chapter9_operator_overloading_conventions


// one of the most unique and powerful in Kotlin: delegated properties

fun main() {
    val person = Person()
    person.name = "oli"
    person.setAttribute("eins","111")
    println(person)
}

class Person {
    private val _attributes = mutableMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }
    var name: String by _attributes          // delegates storing to the map

    override fun toString(): String {
        return "Person(_attributes=$_attributes, name='$name')"
    }
}



////////// lazy initialization


/*
class PersonWithBackedProperty(val name: String) {
    private var _emails: List<Email>? = null

    val emails: List<Email>
        get() {
            if (_emails == null) {                         // not thread safe
                _emails = loadEmails(this)
            }
            return _emails!!
        }
}
*/
class PersonWithDelegateProperty(val name: String) {
    val emails by lazy { /* loadEmails(this) */ }
}

//////////  + observables

