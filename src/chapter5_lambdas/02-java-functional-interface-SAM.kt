package chapter5_lambdas



// Functional Interfaces or Single Abstract Methods (SAM-Interfaces)


/* Java
public interface OnClickListener {
    void onClick(View v);
}
*/

/* Before Java 8

button.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        /* ... */
    }
}

*/

/* Only since Java 8
button.setOnClickListener(view -> { /* ... */ });

 */

// Kotlin
//  button.setOnClickListener { view -> /* ... */ }


fun createAllDoneRunnable(): Runnable {
    return  Runnable { println("All done!") }
}

fun main() {
    createAllDoneRunnable().run()            // Using a SAM constructor to return a value

    val isOdd = IntCondition { it % 2 != 0 }
    println(isOdd.check(1))
    println(isOdd.checkString("2"))
    println(isOdd.checkChar('3'))
}


fun interface IntCondition {                                        // Defining SAM interfaces in Kotlin: "fun" interfaces
    fun check(i: Int): Boolean
    fun checkString(s: String) = check(s.toInt())
    fun checkChar(c: Char) = check(c.digitToInt())
}
