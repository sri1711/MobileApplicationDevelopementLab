package com.rajalakshmi.filesdstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fname = findViewById<EditText>(R.id.etFile)
        var sname = findViewById<EditText>(R.id.etName)
        var cgpa = findViewById<EditText>(R.id.etCGPA)

        findViewById<Button>(R.id.btnWrite).setOnClickListener{
            var text_read = findViewById<TextView>(R.id.tvRead)
            try {
                val f = File("/sdcard/${fname.text.toString()}.txt")
                f.createNewFile()
                val fout = FileOutputStream(f)
                var message = "StudentName: "+ sname.text.toString() +"CGPA: "+ cgpa.text.toString()
                fout.write(message.toString().toByteArray())
                fout.close()
                text_read.text = "Data Written in SDCARD"
                Toast.makeText(baseContext, "Data Written in SDCARD", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Log.e("SRI1711",e.toString())
                Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
            }
        }


        findViewById<Button>(R.id.btnRead).setOnClickListener {
            var buf = ""
            var text_read = findViewById<TextView>(R.id.tvRead)
            try {

                val f = File("/sdcard/${fname.text.toString()}.txt")
                val fin = FileInputStream(f)
                val br = BufferedReader(InputStreamReader(fin))
                var message = br.readLine()
                while (message!=null) {
                    buf += message
                    message = br.readLine()
                }
                text_read.text = buf
                br.close()
                fin.close()
                Toast.makeText(baseContext, "Data Recived from SDCARD", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
            }

        }
    }
}