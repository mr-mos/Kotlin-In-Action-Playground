package chapter3_functions

fun String.lastChar(): Char = this.get(this.length - 1)       // extension function; would be a static method like  FileNameKt.lastChar(String receiver)
val String.lastChar: Char                                     // extension property;   FileNameKt.getLastChar(receiver)
    get() = this.get(length - 1)
fun String.addSomething(): String {
    return "${this}_WOW"
}


fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    println("Hallo Mos".lastChar())
    println("Hallo Mos".lastChar)
    println("Hallo Mos".addSomething())

    val list = listOf(1, 2, 3)
    println(
        list.joinToString("; ", "(", ")")
    )
}