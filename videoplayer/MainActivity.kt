package com.ak.cameraapp

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import android.Manifest
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*

class MainActivity : AppCompatActivity() {
    private val cameraRequest = 1888
    lateinit var imageView: ImageView
    private val videoRequest = 1889
    private lateinit var videoUri: Uri
    var activityResultLauncher: ActivityResultLauncher<Intent>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "KotlinApp"

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), videoRequest)
        }

        val videoButton: Button = findViewById(R.id.button)
        videoButton.setOnClickListener {
            val videoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            startActivityForResult(videoIntent, videoRequest)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == videoRequest && resultCode == RESULT_OK) {
            videoUri = data?.data!!
            // Do something with the videoUri, such as display it in a VideoView
        }
    }
}
