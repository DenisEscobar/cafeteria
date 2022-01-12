package com.example.mykotlinapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.databinding.ActivityMainBinding
import com.example.mykotlinapplication.sharedPref.SharedApp

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
        mytextName.sql=""
        val nom = binding.editTextName
        boton.setOnClickListener{
            //binding.editTextName.setText(mytextName.sql);
            //SharedApp.prefs.name = binding.editTextName.text.toString()
            binding.textsql.text=""
            if (!binding.editTextName.text.toString().equals("")&&!binding.editTextTextPassword.text.toString().equals("")) {
                val application = requireNotNull(this).application
                val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
                val viewModelFactory = RoomViewModelFactory(dataSource, application)
                val roomViewModel =
                    ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)
                binding.setLifecycleOwner(this)
                var a=roomViewModel.onLoginUser(
                    binding.editTextName.text.toString(),
                    roomViewModel.encriptar(binding.editTextTextPassword.text.toString())
                )
//roomViewModel.firstupdate()
                if(a=="ok") {
                    SharedApp.prefs.name=roomViewModel.getuser(binding.editTextName.text.toString())
                    binding.editTextName.setText("")
                    binding.editTextTextPassword.setText("")
                    val intent = Intent(this, logged::class.java)
                    startActivity(intent)
                }else{
                    binding.textsql.text="Convinacion Incorrecta"
                    binding.textsql.setTextColor(Color.parseColor("#CC0000"))
                }
            }else{
                binding.textsql.text="Tienes que añadir datos a todos los campos"
                binding.textsql.setTextColor(Color.parseColor("#CC0000"))
            }
        }
    }
}