package com.example.calculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.ArithmeticException
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    var operator = ""
    var LastNum = false
    var lastDot = false
    var visi = 0;

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

    fun sciEnable(view: View){
        var l = findViewById<LinearLayout>(R.id.ll_sci_layout)
        if(visi == 0) {
            l.visibility = View.VISIBLE
            visi = 1
        }
        else{
            l.visibility =View.GONE
            visi = 0
        }

    }

    fun SciOperation(view: View){
        var result = findViewById<TextView>(R.id.result)
        var inputText = findViewById<TextView>(R.id.TextInput)
        var text = (view as Button).text.toString() + '('
        result.append(text)

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
            var input  = result.text.toString() + ')'
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
                else if(input.contains("sin")){
                    Log.i("SRI1711","ENteR")
                    Log.i("SRI1711",
                        input.subSequence(input.indexOf('(')+1,input.indexOf(')')).toString()
                    )
                    //var res =""
                    var res = sin(input.subSequence(input.indexOf('(')+1,input.indexOf(')')).toString().toDouble())
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("cos")){
                    var res = cos(input.subSequence(input.indexOf('(')+1,input.indexOf(')')).toString().toDouble())
                    result.text = zeroRemover(res.toString())
                }
                else if(input.toLowerCase().contains("log")){
                    var res = ln(input.subSequence(input.indexOf('(')+1,input.indexOf(')')).toString().toDouble())/2.303
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("tan")){
                    var res = tan(input.subSequence(input.indexOf('(')+1,input.indexOf(')')).toString().toDouble())
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("ln")){
                    var res = ln(input.subSequence(input.indexOf('(')+1,input.indexOf(')')).toString().toDouble())
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("sqrt")){
                    var res = sqrt(input.subSequence(input.indexOf('(')+1,input.indexOf(')')).toString().toDouble())
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("mod")){
                    var num = input.split("mod")
                    var num1 = num[0]
                    var num2 = num[1].subSequence(1,num[1].length-1).toString()
                    if(!prefix.isEmpty()){
                        num1 = prefix + num1
                    }
                    var res = num1.toDouble().rem(num2.toDouble())
                    result.text = zeroRemover(res.toString())
                }
                else if(input.contains("^")){
                    var num = input.split("^")
                    var num1 = num[0]
                    Log.i("SRI1711",num[1])
                    var num2 = num[1].subSequence(1,num[1].length-1).toString()
                    var res = num1.toDouble().pow(num2.toDouble())
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