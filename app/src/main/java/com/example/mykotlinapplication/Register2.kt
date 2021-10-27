package com.example.mykotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Register2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val botonback = findViewById<Button>(R.id.buttonback) as Button
        botonback.setOnClickListener{
            finish()
        }
    }
}