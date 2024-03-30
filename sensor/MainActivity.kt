package com.example.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var sm: SensorManager? = null
    private var textView1: TextView? = null
    private lateinit var list: List<Sensor>
    private val sel = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Do nothing for this example
        }

        override fun onSensorChanged(event: SensorEvent) {
            val values = event.values
            textView1?.text = "x: ${values[0]}\ny: ${values[1]}\nz: ${values[2]}"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* Get a SensorManager instance */
        sm = getSystemService(SENSOR_SERVICE) as SensorManager

        textView1 = findViewById(R.id.textView1)

        list = sm!!.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if (list.isNotEmpty()) {
            sm!!.registerListener(sel, list[0], SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(baseContext, "Error: No Accelerometer.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        if (list.isNotEmpty()) {
            sm?.unregisterListener(sel)
        }
        super.onStop()
    }
}