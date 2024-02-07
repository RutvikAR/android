package com.example.testapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tx1 = findViewById<EditText>(R.id.first)
        val tx2 = findViewById<EditText>(R.id.second)


        val savebtn = findViewById<Button>(R.id.save)
        val showbtn = findViewById<Button>(R.id.show)
        savebtn.setOnClickListener() {
            val file: String = tx1.text.toString()
            val data: String = tx2.text.toString()
            val fileOutputStream: FileOutputStream
            try {
                val fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        showbtn.setOnClickListener() {
            val filename:String = tx1.text.toString()
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput(filename)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
//Displaying data on EditText
            tx2.setText(stringBuilder.toString()).toString()
        }
    }
}