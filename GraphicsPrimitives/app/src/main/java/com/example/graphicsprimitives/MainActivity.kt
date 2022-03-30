package com.example.graphicsprimitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        var sc = Samplecanvas()



    }

    class Samplecanvas(context: Context?) : View(context) {

        override fun onDraw(canvas: android.graphics.Canvas?) {
            //super.onDraw(canvas)
            var paint = Paint()
            paint.setColor(Color.YELLOW)
            canvas!!.drawCircle(100f,100f,10f,paint)
        }

    }


}