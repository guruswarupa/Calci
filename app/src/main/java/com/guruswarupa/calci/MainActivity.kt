package com.guruswarupa.calci

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.EditText
import kotlin.math.*

class MainActivity : ComponentActivity() {

    lateinit var editText : EditText
    var num1: Float = 0.0f
    var num2: Float = 0.0f
    var isAdd : Boolean = false
    var isSubtract : Boolean = false
    var isMultiply : Boolean = false
    var isDivide : Boolean = false
    var isPercentage : Boolean = false
    var isPower : Boolean = false
    var isUnderRoot : Boolean = false
    var res: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.number_edit_text)

    }

    fun operationFn(view: View){
        when(view.id){
            R.id.button_one->{
                val number = editText.text.toString() + "1"
                editText.setText(number)
            }
            R.id.button_two->{
                val number = editText.text.toString() + "2"
                editText.setText(number)
            }
            R.id.button_three->{
                val number = editText.text.toString() + "3"
                editText.setText(number)
            }
            R.id.button_four->{
                val number = editText.text.toString() + "4"
                editText.setText(number)
            }
            R.id.button_five->{
                val number = editText.text.toString() + "5"
                editText.setText(number)
            }
            R.id.button_six->{
                val number = editText.text.toString() + "6"
                editText.setText(number)
            }
            R.id.button_seven->{
                val number = editText.text.toString() + "7"
                editText.setText(number)
            }
            R.id.button_eight->{
                val number = editText.text.toString() + "8"
                editText.setText(number)
            }
            R.id.button_nine->{
                val number = editText.text.toString() + "9"
                editText.setText(number)
            }
            R.id.button_zero->{
                val number = editText.text.toString() + "0"
                editText.setText(number)
            }
            R.id.button_dot->{
                val number = editText.text.toString() + "."
                editText.setText(number)
            }
            R.id.button_clr->{
                editText.setText("")
            }
            R.id.button_del->{
                val number = editText.text.toString().dropLast(1)
                editText.setText(number)
            }
            R.id.button_add->{
                num1 = editText.text.toString().toFloat()
                editText.setText("")
                isAdd = true
            }
            R.id.button_subtract->{
                num1 = editText.text.toString().toFloat()
                editText.setText("")
                isSubtract = true
            }
            R.id.button_divide->{
                num1 = editText.text.toString().toFloat()
                editText.setText("")
                isDivide = true
            }
            R.id.button_multiply->{
                num1 = editText.text.toString().toFloat()
                editText.setText("")
                isMultiply = true
            }
            R.id.button_percentage->{
                num1 = editText.text.toString().toFloat()
                editText.setText("")
                isPercentage = true
            }
            R.id.button_power->{
                num1 = editText.text.toString().toFloat()
                editText.setText("")
                isPower = true
            }
            R.id.button_equal->{
                num2 = editText.text.toString().toFloat()
                if(isAdd) {
                   res = (num1+num2).toString()
                    isAdd = false
                }else if(isSubtract) {
                    res = (num1 - num2).toString()
                    isSubtract = false
                }else if(isDivide) {
                    res = (num1 / num2).toString()
                    isDivide = false
                }else if(isMultiply) {
                    res = (num1 * num2).toString()
                    isMultiply = false
                }else if(isPercentage){
                    res = (num1 % num2).toString()
                    isPercentage = false
                }else if(isPower){
                    res = num1.pow(num2).toString()
                    isPower = false
                }else if(isUnderRoot){
                    res = sqrt(num1).toString()
                    isUnderRoot = false
                }
                editText.setText(res)
            }
        }
    }
}
