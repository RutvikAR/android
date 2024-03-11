package com.example.rediobutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            var selectedId: Int = radioGroup.getCheckedRadioButtonId()
            var generatedButton = findViewById<RadioButton>(selectedId)
            if (selectedId == -1) {
                Toast.makeText(applicationContext, "Nothing selected", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(
                    applicationContext,
                    generatedButton.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}