package com.example.mykotlinapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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


}