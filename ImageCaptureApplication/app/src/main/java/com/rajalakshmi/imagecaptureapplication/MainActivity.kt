package com.rajalakshmi.imagecaptureapplication

import android.Manifest
import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult

import androidx.core.app.ActivityCompat.requestPermissions

import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import androidx.annotation.RequiresApi
import android.graphics.Bitmap

import android.app.Activity
import android.util.Log

import androidx.annotation.NonNull
import android.R.attr.data
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.requestPermissions
import android.R.attr.data








class MainActivity : AppCompatActivity() {

    private val CAMERA_REQUEST = 1888
    private var imageView: ImageView? = null
    private val MY_CAMERA_PERMISSION_CODE = 100

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        val ibtn_profile = findViewById<Button>(R.id.btn_select_image)
        ibtn_profile.setOnClickListener{
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    val permission = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permission, MY_CAMERA_PERMISSION_CODE)


            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1888 && resultCode== Activity.RESULT_OK && data!=null){
            Log.i("info", "Photo Selected")
            var bitmap : Bitmap = data.extras!!.get("data") as Bitmap
            Log.i("SRI1711",bitmap.toString())
            imageView!!.setImageBitmap(bitmap)



        }
    }


}