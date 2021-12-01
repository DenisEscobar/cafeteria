package com.example.mykotlinapplication

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapplication.DataBase.Comanda
import com.example.mykotlinapplication.DataBase.ComandaDatabaseDao
import kotlinx.coroutines.launch

class RoomViewModel (
    val database: ComandaDatabaseDao,
    application: Application
) : AndroidViewModel(application){
    private var comanda = MutableLiveData<Comanda?>()
    init {
        initializeComanda()
    }
    private fun initializeComanda() {
        viewModelScope.launch {
            comanda.value = getComandaFromDatabase()
        }
    }
    private suspend fun getComandaFromDatabase(): Comanda? {
        var Comanda = database.getTocomanda()
        return Comanda
    }
    fun verurecomanda(){
        viewModelScope.launch {
            comanda.value = getComandaFromDatabase()
        }
    }
    fun onenviacomanda(p1:String,p2:String,p3:String) {
        viewModelScope.launch {
            val newComanda = Comanda()
            newComanda.primerplato=p1
            newComanda.segundoplato=p2
            newComanda.tercerplato=p3
            insert(newComanda)
            //comanda.value = getComandaFromDatabase()

            var toast= Toast.makeText(getApplication(), "Guardado",Toast.LENGTH_SHORT)
            toast.show()
        }
    }
    fun oncancelar() {
        viewModelScope.launch {
            var toast= Toast.makeText(getApplication(), "Cancelado",Toast.LENGTH_SHORT)
            toast.show()
        }
    }
    private suspend fun insert(comanda: Comanda){
        database.insert(comanda)
    }

}