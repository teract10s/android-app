package com.example.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Tuesday : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuesday)

        val mondayActButton: ImageButton = findViewById(R.id.buttonLeftTuesday)
        mondayActButton.setOnClickListener{
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }
    }
}