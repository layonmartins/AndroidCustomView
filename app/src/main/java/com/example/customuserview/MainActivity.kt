package com.example.customuserview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customView : CustomSquareView = findViewById(R.id.constraint_custom)

        customView.buttonTouched = {
            Log.d("layonf", "button touched")
        }

        customView.sliderChanged = {
            Log.d("layonf", "Slider changed: $it")
        }
    }
}