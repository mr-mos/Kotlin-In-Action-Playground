package chapter6_collections


fun main() {

// 1 filter & map
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 })
    println(list.filterNot { it % 2 == 0 })
    println(list.map { it * it })
    println("Org: $list")
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.map(Person::name))
    println(people.filter {
        val oldestPerson =
            people.maxByOrNull(Person::age)              // do it just once -- outside of the lambda with: val maxAge = people.maxByOrNull(Person::age)?.age
        it.age == oldestPerson?.age
    })
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
    val filtered = numbers.filterIndexed { index, element ->
        index % 2 == 0 && element > 3
    }
    println(filtered)
    val mapped = numbers.mapIndexed { index, element ->
        index + element
    }
    println(mapped)
    val mapNumbers = mapOf(0 to "zero", 1 to "one")
    println(mapNumbers.mapValues { it.value.uppercase() })     // same with the "map" function:  mapNumbers.map { it.key to it.value.uppercase() }.toMap()

// 2 Accumulating values: reduce + fold
    println()
    val sum: Int = numbers.reduce { acc, element -> acc + element }
    println(sum)
    val product: Int = numbers.fold(10) { acc, element: Int -> acc + element }
    println(product)
    val sumRunning = numbers.runningReduce { acc, element -> acc + element }
    println(sumRunning)

// 3 Applying a predicate: all, any, none, count, and find
    println()
    println(numbers.all { it > 0 })
    println(numbers.any { it < 0 })
    println(numbers.none { num: Int -> num < 0 })
    println(numbers.count { it % 2 == 0 })
    println(numbers.find { it > 5 })
    println(numbers.firstOrNull { it > 2 })          // synonym for find()
    println(emptyList<Int>().any { it > 43 })        // empty lists
    println(emptyList<Int>().all { it > 42 })        // always true  "vacuous truth"

// 4 Grouping: partition
    println()
    val (even, odd) = numbers.partition { it % 2 == 0 }
    println(even)
    println(odd)

// 5 Grouping: groupBy
    println()
    val grouped: Map<Int, List<Int>> = numbers.groupBy { it % 3 }
    println(grouped)

// 6 Transforming collections into maps: associate, associateWith, and associateBy
    println()
    val people2 = listOf(
        Person("Joe", 22),
        Person("Mary", 31),
        Person("Jamie", 22)
    )
    val mapNames: Map<String, Int> = people2.associate { it.name to it.age }
    println(mapNames)
    val personToAge = people.associateWith { it.age }
    println(personToAge)
    val ageToPerson = people.associateBy { it.age }
    println(ageToPerson)

// 7 Replacing elements in mutable collections: replaceAll & fill
    println()
    val names = mutableListOf("Martin", "Samuel")
    println(names)
    names.replaceAll { it.uppercase() }
    names.fill("(redacted)")
    println(names)

// 8 ifEmpty
    println()
    val defaultNames = listOf("John", "Jane")
    names.clear()
    println(names.ifEmpty { defaultNames })


// 9 Splitting: chunked & windowed
    println()
    val chunks: List<List<Int>> = numbers.chunked(3)
    println(chunks)
    val chunkSum = numbers.chunked(3) { it.sum() }
    println(chunkSum)
    val windows: List<List<Int>> = numbers.windowed(3)
    println(windows)

// 10 Merging: zip
    println()
    val names2 = listOf("Joe", "Mary", "Jamie")
    val ages = listOf(22, 31, 31, 44, 0)
    val zip: List<Pair<String, Int>> = names2.zip(ages)
    println(zip)
    println(names2.zip(ages) { name, age -> Person(name, age) })
    println(names2 zip ages)
    println(names2 to ages)

// 11 flatMap & flatten
    println()
    println (listOf(listOf("one","two"), listOf("three","four")).flatten())
    val nonFlatMapped= numbers.map { listOf(it, it + 1) }
    println(nonFlatMapped)
    val flatMapped: List<Int> = numbers.flatMap { listOf(it, it + 1) }
    println(flatMapped)
    println(flatMapped.toSet())         // remove duplicates

}


data class Person(val name: String, val age: Int)