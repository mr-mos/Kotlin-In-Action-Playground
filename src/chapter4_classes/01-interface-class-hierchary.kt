package chapter4_classes

import java.io.Serializable


fun main() {
    val button = Button()
    button.click()
    button.showOff()
    button.setFocus(true)
}


interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable")
}

interface Focusable {
    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}


class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

open class RichButton : Clickable { 
    fun disable() { /* ... */ } 
    open fun animate() { /* ... */ }                // function or a property can be overridden; otherwise it´s final !
    override fun click() { /* ... */ } 
}

class ThemedButton : RichButton() {
    final override fun showOff() {
        super.showOff()
    }
    override fun animate() {
        super.animate()
    }
    override fun click() {
        super.click()
    }
}



abstract class Animated { 
    abstract val animationSpeed: Double 
    val keyframes: Int = 20 
    open val frames: Int = 60 

    abstract fun animate() 
    open fun stopAnimating() { /* ... */ } 
    fun animateTwice() { /* ... */ } 
}


internal open class TalkativeButton {             // internal == just visible in module; A module is a set of Kotlin files compiled together. That could be a Gradle source set, a Maven project, or an IntelliJ IDEA module.
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

/*fun TalkativeButton.giveSpeech() {
    yell()
    whisper()
}*/



interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) { /* ... */ }
}

class Button2 : View {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) { /*...*/ }
    /*inner*/ class ButtonState : State { /*...*/ }    //  use "inner" if it should be an "inner class" (references outer class this@Button2) instead of a "nested class"
}



sealed class Expr   //  could also use "interface"  no subclasses are allowed outsite this file
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr() //

fun eval(e: Expr): Int =
    when (e) { //
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
    }