package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var operator = ""
    var LastNum = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun OnBackspace(view: View){
        var result = findViewById<TextView>(R.id.result)
        if(result.text.isEmpty()){
            result.text = ""
        }
        else {
            result.text = result.text.substring(0, ((result.text).length - 1))
        }
    }


    fun OnDigit(view: View ){
        var result = findViewById<TextView>(R.id.result)
        result.append((view as Button).text)
        LastNum = true

    }

    fun OnClear(view: View){
        var result = findViewById<TextView>(R.id.result)
        var inputText = findViewById<TextView>(R.id.TextInput)
        result.text = ""
        inputText.text = ""
        LastNum = false
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        if(LastNum && !lastDot){
            var result = findViewById<TextView>(R.id.result)
            result.append(".")
            LastNum = false
            lastDot = true
        }
    }

    fun OnOperator(view: View){
        var result = findViewById<TextView>(R.id.result)
        if(LastNum && !isOperator(result.text.toString())){
            result.append((view as Button).text)
            LastNum = false
            lastDot = false
        }
    }

    private fun isOperator(value: String) : Boolean{
        return if(value.startsWith("-")) {
            false
        }else{
            value.contains("/") || value.contains("X") || value.contains("+") || value.contains("-") || value.contains("%")
        }
    }

    fun zeroRemover(result: String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }

    fun onEqual(view: View){
        if(LastNum){
            var result =  findViewById<TextView>(R.id.result)
            var input  = result.text.toString()
            var inputText = findViewById<TextView>(R.id.TextInput)
            inputText.text = input
            var prefix = ""
            try{
                if(input.startsWith("-")){
                    prefix = "-"
                    input = input.substring(1)
                }
                if(input.contains("+")){
                    var num = input.split("+")
                    var num1 = num[0]
                    var num2 = num[1]
                    if(!prefix.isEmpty()){
                        num1 = prefix + num1
                    }
                    var res = num1.toDouble() + num2.toDouble()
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("-")){
                    var num = input.split("-")
                    var num1 = num[0]
                    var num2 = num[1]
                    if(!prefix.isEmpty()){
                        num1 = prefix + num1
                    }
                    var res = num1.toDouble() - num2.toDouble()
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("*")){
                    var num = input.split("*")
                    var num1 = num[0]
                    var num2 = num[1]
                    if(!prefix.isEmpty()){
                        num1 = prefix + num1
                    }
                    var res = num1.toDouble() * num2.toDouble()
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("/")){
                    var num = input.split("/")
                    var num1 = num[0]
                    var num2 = num[1]
                    if(!prefix.isEmpty()){
                        num1 = prefix + num1
                    }
                    var res = num1.toDouble() / num2.toDouble()
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("%")){
                    var num = input.split("%")
                    var num1 = num[0]
                    var num2 = num[1]
                    if(!prefix.isEmpty()){
                        num1 = prefix + num1
                    }
                    var res = num1.toDouble() % num2.toDouble()
                    result.text = zeroRemover(res.toString())
                }
            }
            catch(e:ArithmeticException){
                var result = findViewById<TextView>(R.id.result)
                result.text = "error"
                e.printStackTrace()
            }
        }
    }
}