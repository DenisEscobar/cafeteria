package com.example.mykotlinapplication

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mykotlinapplication.DataBase.Comanda
import com.example.mykotlinapplication.DataBase.ComandaDatabaseDao
import com.example.mykotlinapplication.DataBase.log
import kotlinx.coroutines.launch

class RoomViewModel (
    val database: ComandaDatabaseDao,
    application: Application
) : AndroidViewModel(application){
    private var comanda = MutableLiveData<Comanda?>()
    private val coma = database.getAllcomanda()

    private var infocorrecte=MutableLiveData<String>()
    fun setinfo(text: String) {
        infocorrecte.value = text
    }
    fun getinfo(): String {
        return ""+infocorrecte.value
    }

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

    fun log(name:String, pass:String){
        viewModelScope.launch {
            var a = comprobaruser(name, pass)
            setinfo(a)
        }
    }
    private fun comprobaruser(name:String, pass: String): String {
        if(pass==database.getpasswd(name)) {
            return "ok"
        }
        return "ko"
    }
    fun onRegisterUser(u:String, p:String, e:String) {
        viewModelScope.launch {
            val newuser = log()
            newuser.Usuari=u
            newuser.Password=p
            newuser.Email=e
            insertuser(newuser)
        }
    }
    private fun insertuser(user: log) {
        database.insertuser(user)
    }

    fun onLoginUser(user: String, pass: String): String {
        if(pass==database.getpasswd(user)) {
            return "ok"
        }
        return "ko"
    }
}