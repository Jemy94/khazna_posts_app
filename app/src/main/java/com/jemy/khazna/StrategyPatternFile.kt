package com.jemy.khazna

/*In Strategy pattern, a class behavior or its algorithm can be changed at run time.
 This type of design pattern comes under behavior pattern.
In Strategy pattern, we create objects which represent various strategies and a context object
 whose behavior varies as per its strategy object.
 The strategy object changes the executing algorithm of the context object.
*/

// example bellow

interface Strategy {
    fun doOperation(num1: Int, num2: Int): Int
}

class OperationAdd : Strategy {
    override fun doOperation(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}

class OperationMultiply : Strategy {
    override fun doOperation(num1: Int, num2: Int): Int {
        return num1 * num2
    }
}

class Context(private val strategy: Strategy) {
    fun executeStrategy(num1: Int, num2: Int): Int {
        return strategy.doOperation(num1, num2)
    }
}

// implementation
    fun main() {
        var context = Context(OperationAdd())
        println("10 + 5 = " + context.executeStrategy(10, 5))

        context = Context(OperationMultiply())
        println("10 * 5 = " + context.executeStrategy(10, 5))
    }
