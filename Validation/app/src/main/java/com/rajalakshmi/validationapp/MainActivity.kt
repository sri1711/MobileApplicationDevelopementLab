package com.rajalakshmi.validationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            var username = findViewById<EditText>(R.id.etUserId).text.toString()
            var password = findViewById<EditText>(R.id.etPassword).text.toString()
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Both Fields should not be empty!",Toast.LENGTH_SHORT).show()
            }
            else{

                if(!Pattern.matches("[a-zA-z]+",username)){
                    Toast.makeText(this,"Invalid Username!",Toast.LENGTH_SHORT).show()
                }
                else if(!Pattern.matches("[0-9]{4}",password)){
                    Toast.makeText(this,"Invalid Password!",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Welcome Home $username",Toast.LENGTH_SHORT).show()
                    val intent  = Intent(this,HomeActivity::class.java)
                    intent.putExtra("uname",username)
                    startActivity(intent)

                }

            }
        }
    }

}