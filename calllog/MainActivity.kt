package com.example.calllogread

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CallLog
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Date
import android.Manifest


class MainActivity : AppCompatActivity() {
    private val permission: String = Manifest.permission.READ_CALL_LOG
    private val requestCode: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<Button>(R.id.btn1)

        btn1.setOnClickListener() {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_CALL_LOG
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val MY_PERMISSIONS_REQUEST_CALL_LOG = 0
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_CALL_LOG),
                    MY_PERMISSIONS_REQUEST_CALL_LOG
                )
            } else {
                getCallDetails()
            }
        }

    }

    private fun getCallDetails() {
        val contentResolver = contentResolver ?: return

        val projection = arrayOf(
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,
            CallLog.Calls.DATE,
            CallLog.Calls.DURATION
        )

        val cursor = contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            projection,
            null,
            null,
            CallLog.Calls.DATE + " DESC" // Sort by date in descending order
        ) ?: return

        cursor.use { cursor ->
            val sb = StringBuilder()
            sb.append("Call Details:\n")

            while (cursor.moveToNext()) {
                val number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                val type = cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE))
                val date = cursor.getLong(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE))
                val duration =
                    cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION))

                val callDayTime = Date(date)
                val dir = when (type) {
                    CallLog.Calls.OUTGOING_TYPE -> "OUTGOING"
                    CallLog.Calls.INCOMING_TYPE -> "INCOMING"
                    CallLog.Calls.MISSED_TYPE -> "MISSED"
                    else -> "UNKNOWN"
                }

                sb.append("\nPhone Number:--- $number\n")
                sb.append("Call Type:--- $dir\n")
                sb.append("Call Date:--- $callDayTime\n")
                sb.append("Call duration in sec :--- $duration\n")
                sb.append("----------------------------------\n")
            }

            // Use the call details string as needed (e.g., display in a TextView)
            val callDetailsTextView = findViewById<TextView>(R.id.callDetailsTextView)
            callDetailsTextView.text = sb.toString()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val MY_PERMISSIONS_REQUEST_CALL_LOG = null
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_LOG) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCallDetails()
            } else {
                // Handle permission denied case
            }
        }
    }
}


