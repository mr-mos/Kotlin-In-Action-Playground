package chapter3_functions



fun main() {
    println("12.345-6.A".split("\\.|-".toRegex()))     // that's how it works in Java
    println("12.345-6.A".split("[.-]".toRegex()))
    // [12, 345, 6, A]
    println("12.345-6.A".split(".","-"))

    parsePath("/test/dir/hello.jpg")
    parsePathRegex("/test/dir/hello.jpg")

    println(kotlinLogo)
}


val kotlinLogo =                          // triple-quoted string  -- multi line String
    """
    | //
    |//
    |/ \
    """.trimIndent()

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}


fun parsePathRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()           // triple-quoted string  -- no need to escape "\"
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}