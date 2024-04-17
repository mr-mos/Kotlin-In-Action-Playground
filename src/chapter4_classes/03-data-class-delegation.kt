package chapter4_classes

fun main() {
    val customer1 = CustomerNormalClass("Alice", 342562)
    println(customer1)
    val customer2 = CustomerNormalClass("Alice", 342562)
    println(customer1 == customer2)

    val customer3 = CustomerDataClass("Alice", 342562)
    println(customer3)
    val customer4 = CustomerDataClass("Alice", 342562)
    println(customer3 == customer4)

    val bob = CustomerDataClass("Bob", 973293)
    println(bob.copy(postalCode = 382555))                  // copy immutable object and change a property value

    // use delegation "by"
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 1, 2))
    println("Added ${cset.objectsAdded} objects, ${cset.size} uniques.")
}


// Universal object methods
///////////////////////////

class CustomerNormalClass(val name: String, val postalCode: Int) {
    override fun toString() = "Customer(name=$name, postalCode=$postalCode)"
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is CustomerNormalClass)                         // Just to remind you, the is operator checks whether a value has the specified type, and smart casts other to the Customer type (it is the analogue of instanceof in Java)
            return false
        return name == other.name &&
                postalCode == other.postalCode
    }
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}

data class CustomerDataClass(val name: String, val postalCode: Int)                 // data classes overrides toString(), hashCode() and equals() methods and adds more like:  copy()



// Class delegation; decorator design pattern   using "by"
//////////////////////////////////////////////////////////

// often you need to add behavior to another class, even if it wasn’t designed to be extended. A commonly used way to implement this is known as the Decorator design pattern.
// The essence of the pattern is that a new class is created, implementing the same interface as the original class and storing the instance of the original class as a field.
// Methods in which the behavior of the original class doesn’t need to be modified are forwarded to the original class instance.

class CountingSet<T>(
    private val innerSet: MutableCollection<T> = hashSetOf<T>()
) : MutableCollection<T> by innerSet {

    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

