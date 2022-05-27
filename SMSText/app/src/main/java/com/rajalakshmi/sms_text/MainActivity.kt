package com.rajalakshmi.sms_text

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.app.PendingIntent





class MainActivity : AppCompatActivity() {


    private var imageView: ImageView? = null
    private val PERMISSION_CODE = 100
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phonenumber = findViewById<EditText>(R.id.et_mobile)
        val message = findViewById<EditText>(R.id.et_content)

        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            val permission = arrayOf(Manifest.permission.SEND_SMS)
            requestPermissions(permission, PERMISSION_CODE)
        }

        findViewById<Button>(R.id.btn_sendno).setOnClickListener {
            val number: String = phonenumber.text.toString()
            val msg: String = message.text.toString()
            if (number.isEmpty() || msg.isEmpty()) {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                try {
                    val smsManager: SmsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(number, null, msg, null, null)
                    Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}