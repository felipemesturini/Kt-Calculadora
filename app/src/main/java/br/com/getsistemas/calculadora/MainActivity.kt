package br.com.getsistemas.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

private const val TAG = "Calc"

class MainActivity : AppCompatActivity() {
    private lateinit var displayTextView: TextView
    private lateinit var numberButtons: Array<Button>
    private lateinit var operatorButtons: Array<Button>
    private var mContent: StringBuilder = StringBuilder()
    private var mOperator: OperationEnum = OperationEnum.NONE
    private var mIsOperatorClicked: Boolean = false
    private var mOperandAcumulado: Double = 0.00
    private var mOperand1: Double = 0.00
    private var mOperand2: Double = 0.00
    private var mOperandTemp: Double = 0.00
    private val mNumberFormat = NumberFormat.getInstance()
    private var mExpression = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(MainObserver())
        mNumberFormat.minimumFractionDigits = 0
        mNumberFormat.maximumFractionDigits = 6
        initializeViews()
        displayResult()

    }

    private fun initializeViews() {
        displayTextView = findViewById(R.id.displayTextView)

        numberButtons = arrayOf(
            zeroButton, oneButton, twoButton, threeButton, fourButton,
            fiveButton, sixButton, sevenButton, eightButton, nineButton
        )

        operatorButtons = arrayOf(
            addButton, subButton, mulButton, divButton
        )

        numberButtons.forEach { button ->
            button.setOnClickListener { numberButtonClick(button) }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener { operatorButtonClick(button) }
        }

        equalsButton.setOnClickListener { buttonEqualsClick() }
        clearButton.setOnClickListener { clearButtonClick() }
        plusMinusButton.setOnClickListener { plusMinusButtonClick() }
        dotButton.setOnClickListener { dotButtonClick() }
        delButton.setOnClickListener { delButtonClick() }
    }

    private fun delButtonClick() {
        if (mContent.isEmpty()) return

        mContent.deleteCharAt(mContent.lastIndex)
        displayResult()
    }

    private fun dotButtonClick() {
        if (mContent.isEmpty()) return

        if (!mContent.contains(".")) {
            mContent.append(".")
            displayResult()
        }
    }

    private fun plusMinusButtonClick() {
        if (mContent.isEmpty()) return

        val value = mContent.toString().toInt() * (-1)
        mContent
            .clear()
            .append(value)
        displayResult()
    }

    private fun clearButtonClick() {
        mContent
            .clear()
            .append(0)
        displayResult()
        mContent.clear()
        mIsOperatorClicked = false
        mOperator = OperationEnum.NONE
        mExpression.clear()

    }

    private fun buttonEqualsClick() {
        if (mContent.isEmpty()) return
        val result = calcResult()
        mContent.clear()
        mContent.append(result)
        displayResult()
        mIsOperatorClicked = true
        mOperand1 = 0.00
        mExpression.clear()
        mOperator = OperationEnum.NONE
    }

    private fun operatorButtonClick(button: Button) {
        if (mContent.isEmpty()) return
        
//        if (mOperator != OperationEnum.NONE) {
//            if (mOperator == OperationEnum.MUL) {
//                mOperandTemp = mContent.toString().toDouble()
//
//            }
//            else {
//                val result = calcResult()
//                mOperand1 = 0.00
//                mContent.clear()
//                mContent.append(result)
//                displayResult()
//            }
//        }

        mOperator = when (button.text) {
            "+" -> OperationEnum.ADD
            "-" -> OperationEnum.SUB
            "/" -> OperationEnum.DIV
            "*" -> OperationEnum.MUL
            else -> OperationEnum.NONE
        }

        if (mOperator == OperationEnum.MUL) {

        }

        mIsOperatorClicked = true
        mExpression.add(mContent.toString())
        mExpression.add(mOperator.op)
    }

    private fun numberButtonClick(button: Button) {
        if (mIsOperatorClicked) {
            mOperand1 = mContent.toString().toDouble()
            mContent.clear()
            mIsOperatorClicked = false
        }
        mContent.append(button.text)
        mExpression.add(button.text.toString())
        displayResult()
    }

    private fun displayResult() {
        try {
            val text = if (mContent.toString().isNotEmpty()) mContent.toString() else "0"
            val mValue = text.toDouble()
            displayTextView.text = mNumberFormat.format(mValue)
        } catch (ex: IllegalArgumentException) {
            mContent.clear()
            displayTextView.text = "ERROR"
        }

    }

    private fun calcResult(): Double {
        val operand2 = mContent.toString().toDouble()
        return when (mOperator) {
            OperationEnum.ADD -> mOperand1 + operand2
            OperationEnum.SUB -> mOperand1 - operand2
            OperationEnum.MUL -> mOperand1 * operand2
            OperationEnum.DIV -> mOperand1 / operand2
            else -> 0.00
        }

//        val op1 = ""
//        val op2 = ""
//        val result = ""
//        mExpression.forEach {
//
//        }
    }


    private fun parseExpression() {
        val list = mExpression.listIterator(mExpression.size)
        while (list.hasPrevious()) {
            Log.i()
        }
    }

}

enum class OperationEnum(val op: String) {
    ADD("+"), SUB("-"), MUL("*"), DIV("/"), NONE("")

}