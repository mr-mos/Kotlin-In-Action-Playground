package chapter9_operator_overloading_conventions


class MosClass(var value: String) :Comparable<MosClass> {                  //  you implement the Comparable interface so that the Person objects can be compared not only by Kotlin code but also by Java functions

    // overrides ==
    override fun equals(other: Any?): Boolean {                                          // parent method has already "operator" defined; not needed here
        if (!(other is MosClass)) return false
        return value == other.value || value == "magic" || other.value == "magic"
    }

    override  fun compareTo(other: MosClass): Int {
        return compareValuesBy(
            this, other,
            MosClass::value
        )
    }

}

fun main() {

    val mos1 = MosClass("magic")
    val mos2 = MosClass("sdfdsf")
    println(mos1 == mos2)
    val mos3 = MosClass("Xmagic")
    val mos4 = MosClass("sdfdsf")
    println(mos3 == mos4)

    println(mos1 > mos2)
    println(mos2 > mos4)
    println(mos2 >= mos4)


}