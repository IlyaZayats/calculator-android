package com.example.calc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.ui.tooling.preview.Preview
import com.example.calc.ui.theme.CalcTheme
import java.lang.Math.abs

class MainActivity : ComponentActivity() {

    private lateinit var first: EditText
    private lateinit var second: EditText
    private lateinit var operation: TextView
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        first = findViewById(R.id.editText1)
        second = findViewById(R.id.editText2)
        operation = findViewById(R.id.operationTextView)
        result = findViewById(R.id.resultTextView)

        var res = ""
        var error = false

        fun validation (operand: String, left: String, right: String){
            error = false
            if (operand.isBlank()) {
                operation.requestFocus()
                operation.setError("Input operation")
                error = true
            }
            if (left.isBlank() or (left.filter { it == '.' }.count() > 1)) {
                first.requestFocus()
                first.setError("Invalid data")
                error = true
            }
            if (right.isBlank() or (right.filter { it == '.' }.count() > 1)) {
                second.requestFocus()
                second.setError("Invalid data")
                error = true
            }
        }

        fun calculate(){
            val operand = operation.text.toString()
            val left = first.text.toString()
            val right = second.text.toString()
            validation(operand, left, right)
            if (!error) {
                var s = 0.0F
                if (operand == "+")
                    s = left.toFloat() + right.toFloat()
                else if (operand == "-")
                    s = left.toFloat() - right.toFloat()
                else if (operand == "/")
                    s = left.toFloat() / right.toFloat()
                else if (operand == "*")
                    s = left.toFloat() * right.toFloat()

                if (abs(s - s.toInt()) > 0.000001)
                    res = s.toString()
                else
                    res = s.toInt().toString()
                operation.setText("")
                second.setText("")
            }
        }


        val clickInputs = View.OnClickListener {
            first.setError(null)
            second.setError(null)
            if (operation.text.isBlank())
                first.setText("${first.text.toString()}${(it as Button).text}")
            else
                second.setText("${second.text.toString()}${(it as Button).text}")
            result.setText("")
        }


        val clickOperations = View.OnClickListener {
            operation.setError(null)
            if (operation.text.toString() != "") {
                calculate()
                if (!error) {
                    first.setError(null)
                    first.setText(res)
                }
            }
            operation.setText("${(it as Button).text}")
            second.requestFocus()
            if (first.text.toString() == "") {
                first.setText(res)
                first.setError(null)
            }
            result.setText("")
        }

        val clickResult = View.OnClickListener {
            calculate()
            if (!error) {
                result.setText(res)
                first.setText("")
            }
        }

        val clickDelete = View.OnClickListener {
            error = false
            first.setText("")
            second.setText("")
            result.text = ""
            operation.text = ""
            first.setError(null)
            second.setError(null)
            operation.setError(null)
        }

        findViewById<Button>(R.id.buttonPlus).setOnClickListener(clickOperations)
        findViewById<Button>(R.id.buttonDivison).setOnClickListener(clickOperations)
        findViewById<Button>(R.id.buttonMinus).setOnClickListener(clickOperations)
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener(clickOperations)
        findViewById<Button>(R.id.buttonEquals).setOnClickListener(clickResult)
        findViewById<Button>(R.id.button1).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button2).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button3).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button4).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button5).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button6).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button7).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button8).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button9).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.button0).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.buttonQuote).setOnClickListener(clickInputs)
        findViewById<Button>(R.id.buttonDelete).setOnClickListener(clickDelete)


    }
}