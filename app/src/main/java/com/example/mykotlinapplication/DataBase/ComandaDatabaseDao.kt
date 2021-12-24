package com.example.mykotlinapplication.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ComandaDatabaseDao {
    @Insert
    suspend fun insert(comanda: Comanda)
    @Update
    suspend fun update(Comanda: Comanda)
    @Query("SELECT * from comanda_table WHERE IdComanda = :key")
    suspend fun getAllComandaForClient(key: Long): Comanda?
    @Query("DELETE FROM comanda_table")
    suspend fun clear()
    @Query("SELECT * FROM comanda_table ORDER BY IdComanda DESC LIMIT 1")
    suspend fun getTocomanda(): Comanda?
    @Query("SELECT * FROM comanda_table ORDER BY IdComanda DESC")
    fun getAllcomanda(): List<Comanda>

//usuari
    @Query("SELECT password FROM USER WHERE usuari= :name")
    fun getpasswd(name: String): String
    @Insert
    fun insertuser(user: log)
//plats
    @Query("SELECT * FROM platos WHERE categoria=:tipo")
    fun getplattipus(tipo: String):List<platos>
    @Insert
    fun insertplat(platos: platos)
}