package com.rajalakshmi.androidalertdialog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Confirm")
            //set message for alert dialog
            builder.setMessage("Are you sure to login?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
                var username = findViewById<EditText>(R.id.etUserId).text.toString()
                var password = findViewById<EditText>(R.id.etPassword).text.toString()
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(this,"Both Fields should not be empty!", Toast.LENGTH_SHORT).show()
                }
                else{

                    if(!Pattern.matches("[a-zA-z]+",username)){

                        Toast.makeText(this,"Invalid Username!", Toast.LENGTH_SHORT).show()
                        val intent  = Intent(this,DisplayActivity::class.java)
                        intent.putExtra("uname",username)
                        startActivity(intent)
                    }
                    else if(!Pattern.matches("[0-9]{4}",password)){
                        Toast.makeText(this,"Invalid Password!", Toast.LENGTH_SHORT).show()
                        val intent  = Intent(this,DisplayActivity::class.java)
                        intent.putExtra("uname",username)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"Welcome Home $username", Toast.LENGTH_SHORT).show()
                        val intent  = Intent(this,HomeActivity::class.java)
                        intent.putExtra("uname",username)
                        startActivity(intent)

                    }

                }
            }
            //performing cancel action
            builder.setNeutralButton("Cancel"){dialogInterface , which ->
                Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
            }
            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }
}