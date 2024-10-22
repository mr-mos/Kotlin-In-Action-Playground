package chapter_13_DSL_construction

fun buildStringLambdaAsArgument(
    builderAction: (StringBuilder) -> Unit
): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}


fun buildStringLambdaAsReceiver(
    builderAction: StringBuilder.() -> Unit     // extension function type instead of a regular function type
): String {
    val sb = StringBuilder()
    sb.builderAction()                          //  Instead of passing the object as an argument, you invoke the lambda variable as if it were an extension function
    return sb.toString()
}



fun main() {
    val s1 = buildStringLambdaAsArgument {
        it.append("Hello, ")
        it.append("World!")
        it.append(" With Argument")

    }
    println(s1)


    val s2 = buildString {
        this.append("Hello, ")
        append("World!")
        append(" With Receiver")
    }
    println(s2)

    val map = mutableMapOf(1 to "one")
    // if you don’t care about the result, these functions are actually interchangeable:
    val receiver = map.apply { this[2] = "two"}
    val resultOfLampda = with (map) { this[3] = "three" }
    println(map)

    println(createTable())
    println(createAnotherTable2())

}


@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }

    override fun toString() =
        "<$name>${children.joinToString("")}</$name>"
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}
class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}
class TD : Tag("td")

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

fun createTable() =
    table {
        tr {
            td {
//                td {                              // not possible because of @DslMarker
//                }
            }
        }
    }


fun createAnotherTable2() = table {
    for (i in 1..2) {
        tr {
            td {
            }
        }
    }
}