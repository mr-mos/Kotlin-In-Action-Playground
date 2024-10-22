package chapter_13_DSL_construction

import kotlinx.html.stream.createHTML
import kotlinx.html.*

fun createSimpleTable() = createHTML().
table {
    tr {
        td {
            +"cell"
        }
    }
}
fun createSimpleTableExplicitSyntax() = createHTML().
table {
    this@table.tr {
        this@tr.td {
            +"cell"
        }
    }
}


fun createAnotherTable() = createHTML().table {
    val numbers = mapOf(1 to "one", 2 to "two")
    for ((num, string) in numbers) {
        tr {
            td { +"$num" }
            td { +string }
        }
    }
}

fun main() {
    println(createSimpleTable())
    println(createSimpleTableExplicitSyntax())
    println(createAnotherTable())
}