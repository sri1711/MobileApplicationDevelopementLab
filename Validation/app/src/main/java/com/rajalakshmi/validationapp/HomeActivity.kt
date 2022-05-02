package com.rajalakshmi.validationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var uname = intent.getStringExtra("uname")

        findViewById<TextView>(R.id.tvusername).text = uname.toString()
    }
}