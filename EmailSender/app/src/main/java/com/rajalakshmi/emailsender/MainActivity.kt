package com.rajalakshmi.emailsender

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail =  findViewById<EditText>(R.id.et_mail)
        val etSubject =  findViewById<EditText>(R.id.et_subjectMessage)
        val etContent =  findViewById<EditText>(R.id.et_content)

        findViewById<Button>(R.id.btn_sendEmail).setOnClickListener {
            val userAddress = etEmail!!.text.toString()
            val userSubject = etSubject!!.text.toString()
            val userMessage = etContent!!.text.toString()

            val emailIntent: Intent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto")
            emailIntent.type = "text/plain"

            val emailAddress  = arrayOf<String>(userAddress)
            print(emailAddress)
            emailIntent.putExtra(Intent.EXTRA_EMAIL,emailAddress)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,userSubject)
            emailIntent.putExtra(Intent.EXTRA_TEXT,userMessage)

            startActivity(Intent.createChooser(emailIntent,"Send Email"))

        }

    }
}