package br.com.getsistemas.calculadora

import java.util.*

val mExpression = mutableListOf<String>()
val mNewExpression = mutableListOf<String>()
val mFilter = mutableListOf<String>()
val operatorList = listOf("/", "*", "+", "-")
val operators = listOf("/", "*", "+", "-")
val valores = Stack<Double>()

//mExpression.add("5")
//mExpression.add("+")
//mExpression.add("5")
//mExpression.add("-")
//mExpression.add("2")
//mExpression.add("/")
mExpression.add("2")
mExpression.add("*")
mExpression.add("2")
//mExpression.add("1")

var result = 0.00
var operador = ""
var valor = 0.00
var found = true

for (op in operatorList) {
    result = 0.00
    operador = ""
    var valor = 0.00
    while (op in mExpression)  {
        println(mExpression)
        val index = mExpression.indexOf(op)
        val valor1 = mExpression.removeAt(index - 1).toDouble()
        val op = mExpression.removeAt(index - 1)
        val valor2 = mExpression.removeAt(index - 1).toDouble()
        println(mExpression)
        val resp = applyOperation(valor1, valor2, op)
        println("resp: $resp")
        mExpression.add(index - 1, resp.toString())
        println(mExpression)
    }
//    for ((index, item) in mExpression.withIndex()) {
//        if (item != op) {
//            continue
//        }
//        val valor1 = mExpression[index - 1].toDouble()
//        val valor2 = mExpression[index + 1].toDouble()
//        operador = item
//        println(mExpression.removeAt(index - 1))
//        val resp = applyOperation(valor1, valor2, operador)
//        println("resp: $resp")
//        println("resp: $mExpression")
//    }

}
println("Result: ${mExpression.first()}")

fun applyOperation(valor1: Double, valor2: Double, op: String): Double {
    return when (op) {
        "+" -> valor1 + valor2
        "-" -> valor1 - valor2
        "/" -> valor1 / valor2
        "*" -> valor1 * valor2
        else -> 0.00
    }
}