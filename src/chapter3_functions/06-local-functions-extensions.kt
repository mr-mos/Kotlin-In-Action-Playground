package chapter3_functions

fun main() {
    useLocalFunctions(User(1,"name","address"))

}

class User(val id: Int, val name: String, val address: String)

fun useLocalFunctions(user: User) {
    fun validate(value: String,        // local function
                 fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user.name, "Name") //
    validate(user.address, "Address")
}