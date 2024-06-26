package chapter7_nullable_values

fun main() {
    println(strLen("hello"))
    println(strLenUnsafe(null))
    // Elvis operator
    val length = strLenUnsafe(null) ?: 0
    println(length)
    // safe-cast operator
    val myTest = "MyString" as? Int ?: "is not a String"
    println(myTest)
    // non-null assertion
    val myString: String? = "checker"
    println(myString!!.length)
    // let-function
    val email: String? = "foo@bar.com"
    //sendEmailTo(email)
    email?.let { sendEmailTo(it) }   // The let function will be called only if the email value is non-null,
    //  Late-initialized properties
    lateinit var name: String
    name = "John Doe"
    name = "22"
    println(name.length)    // UninitializedPropertyAccessException instead of a Nullpointer Exception
    // Extensions for nullable types
    println(name.isNullOrShort())
    var name2: String? = null
    println(name2.isNullOrEmpty())          // extension methods allow to be called on null values (not possible in Java)
    // Nullability of type parameters
    printHashCode(null)
    // Java:
    // -- If there are information or annotations like @Nullable; Kotlin will know about his
    // --  Platform types
    // --- Can be nullable or non-nullable; it's up to the developer; Kotlin is not forcing any checks or makes any assumptions
    // --- You can’t declare a variable of a platform type in Kotlin; these types can only come from Java code
    // ---  "String!" means platform type like in ERROR: Type mismatch: inferred type is String! but Int was expected
    val getenv : String = System.getenv("something")
    val getenv2 : String? = System.getenv("something")

}


fun strLen(s: String) = s.length
fun strLenUnsafe(s: String?) = s?.length       //  safe-call operator: ?.

fun sendEmailTo(email: String) = println(email)

fun String?.isNullOrShort(): Boolean =
    this == null || this.length < 5

fun <T> printHashCode(t: T) {                 // allowed to be null, even though the type parameter T doesn’t end with a question mark
//    same as: fun <T : Any?> printHashCode(t: T) {
//    make it non null: fun <T : Any> printHashCode(t: T) {
        println(t?.hashCode())
}