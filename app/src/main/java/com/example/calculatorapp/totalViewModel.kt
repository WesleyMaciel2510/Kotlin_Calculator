package com.example.calculatorapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class TotalViewModel : ViewModel() {
    private val _total = mutableStateOf("0")
    val total = _total
    private var totalIsUsed = false

    // Validate what is typed
    fun handleInput(input: String) {
        Log.d("calculate", "handleInput called")

        when {
            input.isNumber() -> {
                Log.d("calculate", "input.isNumber")

                if (totalIsUsed) {
                    _total.value = input
                    totalIsUsed = false
                } else {
                    appendNumber(input)
                }
            }

            input == "C" -> clearTotal()
            input == "=" -> evaluateTotal()
            input == "()" -> handleParentheses()
            input == "±" -> toggleSign()
            input == "/" -> handleDivision()
            else -> addToTotal(input)
        }
    }

    private fun appendNumber(number: String) {
        // Append the number to the current total
        if (_total.value == "0" || totalIsUsed) {
            _total.value = number
            totalIsUsed = false
        } else {
            _total.value += number
        }
        _total.value = formatNumberWithCommas(_total.value)
    }

    private fun evaluateTotal() {
        Log.d("calculate", "evaluateTotal called")

        // Preprocess the input to handle percentage operators
        val preprocessedInput = preprocessInput(_total.value)

        try {
            val result = ExpressionBuilder(preprocessedInput).build().evaluate()

            // Format the result to remove ".0" for integers
            val formattedResult = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }
            _total.value = formattedResult
            Log.d("calculate", "TOTAL = ${_total.value}")

            totalIsUsed = true
        } catch (e: Exception) {
            _total.value = "Error"
            totalIsUsed = false
        }
    }

    private fun preprocessInput(input: String): String {
        // Handle percentages
        val percentageRegex = Regex("""(\d+(\.\d+)?)%(\d+(\.\d+)?)""")
        var preprocessedInput = input

        // Convert percentages to a form suitable for ExpressionBuilder
        percentageRegex.findAll(input).forEach {
            val fullMatch = it.value
            val value = it.groupValues[1]
            val operand = it.groupValues[3]
            val replacement = "($value*0.01*$operand)"
            preprocessedInput = preprocessedInput.replace(fullMatch, replacement)
        }

        // Remove commas and unnecessary spaces
        preprocessedInput = preprocessedInput.replace(",", "").replace("\\s+".toRegex(), "")

        return preprocessedInput
    }

    private fun handleParentheses() {
        Log.d("calculate", "handleParentheses called")
        // Check if _total.value ends with a number (check for digit)
        val endsWithNumber = _total.value.isNotEmpty() && _total.value.last().isDigit()

        // Check if there are any open parentheses in _total.value
        val hasOpenParenthesis = _total.value.count { it == '(' } > 0

        // Determine whether to add an opening or closing parenthesis
        val charToAdd = if (endsWithNumber) {
            if (hasOpenParenthesis) ")" else "X("
        } else {
            if (hasOpenParenthesis) ")" else "("
        }

        _total.value += charToAdd
    }

    private fun toggleSign() {
        Log.d("calculate", "toggleSign called")
        try {
            // Parse the current _total.value to double
            var currentValue = _total.value.toDouble()

            // Toggle the sign of the value
            currentValue *= -1

            // Format the result to remove ".0" for integers
            val formattedResult = if (currentValue % 1 == 0.0) {
                currentValue.toInt().toString()
            } else {
                currentValue.toString()
            }

            // Update _total.value with the new formatted value
            _total.value = formattedResult
        } catch (e: NumberFormatException) {
            // Handle if _total.value cannot be parsed to double
            _total.value = "Error"
        }
    }

    private fun handleDivision() {
        Log.d("calculate", "handleDivision called")
        // Check if _total.value is not starting with '0'
        if (_total.value.isNotEmpty() && _total.value.first() != '0') {
            _total.value += "/"
        }
    }

    private fun clearTotal() {
        _total.value = "0"
        totalIsUsed = false
    }

    private fun addToTotal(input: String) {
        Log.d("calculate", "addToTotal called")
        val potentialTotal = _total.value + input

        if (input.isDigit() || (input == "." && !_total.value.contains("."))) {
            Log.d("calculate", "entrou no if")

            if (_total.value == "0" || totalIsUsed) {
                _total.value = ""
            }

            _total.value += input
            totalIsUsed = false
            _total.value = formatNumberWithCommas(_total.value)
        } else {
            if (totalIsUsed) {
                totalIsUsed = false
            }
            _total.value += input
        }
    }

    private fun String.isDigit(): Boolean {
        return this.all { it.isDigit() }
    }

    private fun String.isNumber(): Boolean {
        return this.toDoubleOrNull() != null
    }

}

private fun formatNumberWithCommas(value: String): String {
    val parts = value.split("[-+*/()]".toRegex())
    val formattedParts = parts.map { part ->
        part.toDoubleOrNull()?.let {
            if (it % 1 == 0.0) {
                it.toInt().toString()
            } else {
                String.format("%.2f", it)
            }
        } ?: part
    }
    return value.replace("[-+*/()]".toRegex()) {
        val index = it.range.first
        formattedParts.getOrNull(index) ?: it.value
    }
}


