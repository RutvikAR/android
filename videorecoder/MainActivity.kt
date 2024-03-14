package com.ak.videorecoder11

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
//    private var recordVideoBtn: Button? = null
//    private val videoView: VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing variables on below line.
        val recordVideoBtn = findViewById<Button>(R.id.idBtnRecordVideo);



        // adding click listener for recording button.
        recordVideoBtn.setOnClickListener(View.OnClickListener { // on below line opening an intent to capture a video.
            val i = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            // on below line starting an activity for result.
            startActivityForResult(i, 1)
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            // Setting video URI for our video view.
            val videoView = findViewById<VideoView>(R.id.videoView);
            videoView.setVideoURI(data?.data)
            // Starting the video view.
            videoView.start()
        }
    }

}
