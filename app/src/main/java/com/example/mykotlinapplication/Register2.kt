package com.example.mykotlinapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.sharedPref.SharedApp

class Register2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val botonregistrar = findViewById<Button>(R.id.buttonregister) as Button
        var textnom= findViewById<EditText>(R.id.editTextname) as EditText
        var textpass= findViewById<EditText>(R.id.editTextPassword) as EditText
        var textemail= findViewById<EditText>(R.id.editTextEmail) as EditText
        var texterror= findViewById<TextView>(R.id.texterror) as TextView
        botonregistrar.setOnClickListener {
            if(!(textnom.text.toString().isEmpty() || textpass.text.toString().isEmpty() || textemail.text.toString().isEmpty())){

                val application = requireNotNull(this).application
                val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
                val viewModelFactory = RoomViewModelFactory(dataSource, application)
                val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)

                var existe:Boolean=false
                val a = roomViewModel.getuser()
                for(i in a){
                    if(i.Usuari==textnom.text.toString() || i.Email==textemail.text.toString()){
                        existe=true
                    }
                }

                if(existe){
                    texterror.text = "Usuario Existente"
                    texterror.setTextColor(Color.parseColor("#CC0000"))
                }else{
                    roomViewModel.onRegisterUser(textnom.text.toString(), textpass.text.toString(), textemail.text.toString())

                    SharedApp.prefs.name = textnom.text.toString()
                    textnom.setText("")
                    textemail.setText("")
                    textpass.setText("")
                    val intent = Intent(this, logged::class.java)
                    startActivity(intent)
                }

            }else{
                texterror.text = "Los Campos no pueden estar vacios"
                texterror.setTextColor(Color.parseColor("#CC0000"))
            }
        }


        val botonback = findViewById<Button>(R.id.buttonback) as Button
        botonback.setOnClickListener{
            finish()
        }
    }
}