package com.example.checkbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val p = findViewById<CheckBox>(R.id.chkPizza)
        val h = findViewById<CheckBox>(R.id.chkHotDog)
        val b = findViewById<CheckBox>(R.id.chkBurgur)
        val g = findViewById<Button>(R.id.button)
        g.setOnClickListener {
            var totalamount = 0
            var result = StringBuilder()
            result.append("Selected Items:")
            if (p.isChecked()) {
                result.append("\nPizza 100Rs")
                totalamount += 100
            }
            if (h.isChecked()) {
                result.append("\nHot Dog 80Rs")
                totalamount += 80
            }
            if (b.isChecked()) {
                result.append("\nBurgur 50Rs")
                totalamount += 50
            }
            result.append("\nTotal: " + totalamount + "Rs")
            Toast.makeText(
                applicationContext, totalamount.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}