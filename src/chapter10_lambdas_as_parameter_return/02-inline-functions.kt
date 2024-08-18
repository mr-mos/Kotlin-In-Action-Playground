package chapter10_lambdas_as_parameter_return
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.io.path.Path
import kotlin.io.path.useLines


//  If you mark a function with the inline modifier, the compiler won’t generate a function call when this function is used and, instead, will replace every call to the function with the actual code implementing the function.
//   Using the inline keyword is likely to improve performance only with functions that take lambdas as arguments; all other cases require additional investigation, measuring, and profiling of your application.

fun main() {
    println("Before sync")
    synchronized(ReentrantLock()) {
        println("Action")
    }
    println("After sync")
}



inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

fun readFirstLineFromFile(fileName: String): String {
    Path(fileName).useLines {                      // uses inline; check implementation
        return it.first()
    }
}