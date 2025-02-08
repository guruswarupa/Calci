package com.guruswarupa.calci

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.EditText
import java.util.*

class MainActivity : ComponentActivity() {

    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.number_edit_text)
    }

    fun operationFn(view: View) {
        when (view.id) {
            R.id.button_one -> numberClicked("1")
            R.id.button_two -> numberClicked("2")
            R.id.button_three -> numberClicked("3")
            R.id.button_four -> numberClicked("4")
            R.id.button_five -> numberClicked("5")
            R.id.button_six -> numberClicked("6")
            R.id.button_seven -> numberClicked("7")
            R.id.button_eight -> numberClicked("8")
            R.id.button_nine -> numberClicked("9")
            R.id.button_zero -> numberClicked("0")
            R.id.button_dot -> numberClicked(".")
            R.id.button_clr -> editText.setText("")
            R.id.button_del -> deleteLastChar()
            R.id.button_add -> operatorClicked("+")
            R.id.button_subtract -> operatorClicked("-")
            R.id.button_divide -> operatorClicked("/")
            R.id.button_multiply -> operatorClicked("*")
            R.id.button_percentage -> operatorClicked("%")
            R.id.button_power -> operatorClicked("^")
            R.id.button_equal -> calculateResult()
        }
    }

    private fun numberClicked(value: String) {
        editText.append(value)
    }

    private fun operatorClicked(value: String) {
        val currentText = editText.text.toString()
        if (currentText.isNotEmpty() && currentText.last().toString().matches(Regex("[0-9]"))) {
            editText.append(value)
        }
    }

    private fun deleteLastChar() {
        val text = editText.text.toString()
        if (text.isNotEmpty()) {
            editText.setText(text.dropLast(1))
        }
    }

    private fun calculateResult() {
        val expression = editText.text.toString()
        try {
            val result = evaluateExpression(expression)

            if (result % 1.0 == 0.0) {
                editText.setText(result.toInt().toString())
            } else {
                val formattedResult = String.format("%.6f", result).trimEnd('0').trimEnd('.')
                editText.setText(formattedResult)
            }
        } catch (e: Exception) {
            editText.setText("Error")
        }
    }

    private fun evaluateExpression(expression: String): Double {
        return try {
            val postfix = infixToPostfix(expression)
            evaluatePostfix(postfix)
        } catch (e: Exception) {
            Double.NaN
        }
    }

    private fun infixToPostfix(expression: String): List<String> {
        val precedence = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2, "%" to 2, "^" to 3)
        val output = mutableListOf<String>()
        val operators = Stack<String>()

        val tokens = Regex("\\d+(\\.\\d+)?|[+\\-*/%^()]").findAll(expression).map { it.value }.toList()

        for (token in tokens) {
            when {
                token.matches(Regex("\\d+(\\.\\d+)?")) -> output.add(token)
                token == "(" -> operators.push(token)
                token == ")" -> {
                    while (operators.isNotEmpty() && operators.peek() != "(") {
                        output.add(operators.pop())
                    }
                    operators.pop()
                }
                else -> {
                    while (operators.isNotEmpty() && precedence.getOrDefault(token, 0) <= precedence.getOrDefault(operators.peek(), 0)) {
                        output.add(operators.pop())
                    }
                    operators.push(token)
                }
            }
        }

        while (operators.isNotEmpty()) {
            output.add(operators.pop())
        }
        return output
    }

    private fun evaluatePostfix(postfix: List<String>): Double {
        val stack = Stack<Double>()
        for (token in postfix) {
            when {
                token.matches(Regex("\\d+(\\.\\d+)?")) -> stack.push(token.toDouble())
                else -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(
                        when (token) {
                            "+" -> a + b
                            "-" -> a - b
                            "*" -> a * b
                            "/" -> a / b
                            "%" -> a % b
                            "^" -> Math.pow(a, b)
                            else -> throw IllegalArgumentException("Unknown operator $token")
                        }
                    )
                }
            }
        }
        return stack.pop()
    }
}
