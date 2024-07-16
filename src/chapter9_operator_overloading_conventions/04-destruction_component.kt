package chapter9_operator_overloading_conventions

fun main() {
    val p = Point(10, 20)
    val (x, z) = p             // destructuring declaration
    println(x)
    println(z)

    val p1 = PointWithoutDataClass(10, 20)
    val (a, b) = p1
    println(a)
    println(b)

    val (name, ext) = splitFilename("example.kt")
    println(name)
    println(ext)

    // deconstruction a map
    val map = mapOf("key1" to 111)
    val (key, value) = map.entries.first()
    println(key)
    println(value)
    println(map.entries.first().component2())
    val (_, justTheValue) = map.entries.first()
    println(justTheValue)

}




class PointWithoutDataClass (val x: Int, val y: Int) {
    operator fun component1() = y
    operator fun component2() = x
}


data class NameComponents(val name: String,                          // Alternativ to Pair or Triple
                          val extension: String)

fun splitFilename(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)           // The standard library allows you to use this syntax to access the first five elements of a container.
    return NameComponents(name, extension)
}