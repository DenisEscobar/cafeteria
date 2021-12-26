package com.example.mykotlinapplication

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mykotlinapplication.DataBase.Comanda
import com.example.mykotlinapplication.DataBase.ComandaDatabaseDao
import com.example.mykotlinapplication.DataBase.log
import com.example.mykotlinapplication.DataBase.platos
import kotlinx.coroutines.launch

class RoomViewModel (
    val database: ComandaDatabaseDao,
    application: Application
) : AndroidViewModel(application){
    private var comanda = MutableLiveData<Comanda?>()
fun vercom(): List<Comanda> {
    val coma = database.getAllcomanda()
    return coma
}
fun vercomcli(a:String): List<Comanda> {
    val coma = database.getAllComandaForClient(a)
    return coma
}
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
    fun onenviacomanda(n:String,p1:String,p2:String,p3:String,pr:String) {
        viewModelScope.launch {
            val newComanda = Comanda()
            newComanda.nomclient=n
            newComanda.primerplato=p1
            newComanda.segundoplato=p2
            newComanda.tercerplato=p3
            newComanda.preciototal=pr
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
fun getuser(email:String): String {
    var a= database.getname(email)
    return a
}
    fun onLoginUser(user: String, pass: String): String {
        if(pass==database.getpasswd(user)) {
            return "ok"
        }
        return "ko"
    }

    fun primerplat(tipus: String): List<platos> {
        var a = database.getplattipus(tipus)
        return a
    }
    fun insertarplat(nom:String, preu:String, descripcio:String, categoria:String){
        val plat=platos()
        plat.NomPlato=nom
        plat.PrecioPlato=preu
        plat.DescripcioPlato=descripcio
        plat.CategoriaPlato=categoria
        database.insertplat(plat)
    }

    fun firstupdate(){
        val plat=platos()
        plat.NomPlato="coca-cola"
        plat.PrecioPlato="2"
        plat.DescripcioPlato="cola"
        plat.CategoriaPlato="beguda"
        database.insertplat(plat)
        plat.NomPlato="fanta llimona"
        plat.PrecioPlato="2"
        plat.DescripcioPlato="llimona"
        plat.CategoriaPlato="beguda"
        database.insertplat(plat)
        plat.NomPlato="fanta taronga"
        plat.PrecioPlato="2"
        plat.DescripcioPlato="taronga"
        plat.CategoriaPlato="beguda"
        database.insertplat(plat)

        plat.NomPlato="entrepan fuet"
        plat.PrecioPlato="3"
        plat.DescripcioPlato="fuet"
        plat.CategoriaPlato="entrepan"
        database.insertplat(plat)

        plat.NomPlato="cafe"
        plat.PrecioPlato="1.5"
        plat.DescripcioPlato="cafe"
        plat.CategoriaPlato="postre"
        database.insertplat(plat)



    }
}