/**
 * Basic Reverse Polish Notation (RPN) calculator utilizing a kotter TUI
 * By Connor Brennan 18-02-2025
 */




import com.varabyte.kotter.foundation.*
import com.varabyte.kotter.foundation.input.*
import com.varabyte.kotter.foundation.text.*
import com.varabyte.kotter.runtime.Session



fun main() = session {
    section {
        bold()
        cyan()
        textLine("Welcome to the interactive Kotlin RPN calculator!\n\n")
    }.run()

    //Get n amount of operands before operator
    val calcOperator = selectOperator(2)

}

enum class Operators {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    EXPONENT,
    FACTORIAL;
}


fun Session.selectOperator(numOfOperands: Int): Operators {
    var selectedIndex by liveVarOf(0)
    section {
        black(isBright = true){bold{textLine("Enter operator: \n")}}

        Operators.values().forEachIndexed { index, operator ->
            text(if (selectedIndex == index) ">" else " ")
            text(" ${operator.name.lowercase()}\n")
        }
        //textLine("\nSelected index=${selectedIndex.toString()} Size of Operators= ${Operators.values().size}")
    }.runUntilSignal {
        onKeyPressed {
            when (key) {
                Keys.UP -> selectedIndex--
                Keys.DOWN -> selectedIndex++
                Keys.ENTER -> signal()
            }
            //Check for selection out of range
            if(selectedIndex < 0) selectedIndex = 0
            if(selectedIndex > Operators.values().size-1) selectedIndex = Operators.values().size-1
        }

    }
    return Operators.values()[selectedIndex]
}

