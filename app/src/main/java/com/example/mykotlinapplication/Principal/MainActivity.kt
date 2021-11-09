package com.example.mykotlinapplication.Principal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mytextName: MyName = MyName("sql","b")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = mytextName
        val boton = binding.button
        boton.setText("Adelante")

        val register = binding.textregister
        register.setOnClickListener{
            val intent= Intent(this, Register2::class.java)
            startActivity(intent)
        }
        val nom = binding.editTextName
        boton.setOnClickListener{
            mytextName.sql="a"
            //binding.editTextName.setText(mytextName.sql);

            if(binding.editTextName.getText().toString().equals(mytextName.name)&&binding.editTextTextPassword.getText().toString().equals(mytextName.sql)) {
                val intent = Intent(this, logged::class.java)
                startActivity(intent)
            }
        }
    }
}