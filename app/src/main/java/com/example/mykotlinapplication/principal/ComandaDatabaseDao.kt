package com.example.mykotlinapplication.principal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ComandaDatabaseDao {
    @Insert
    fun insert(comanda: Comanda)
    @Update
    fun update(Comanda: Comanda)
    @Query("SELECT * from comanda_table WHERE IdCliente = :key")
    fun get(key: Long): Comanda?
    @Query("DELETE FROM comanda_table")
    fun clear()
    @Query("SELECT * FROM comanda_table ORDER BY IdCliente DESC LIMIT 1")
    fun getTocomanda(): Comanda?
    @Query("SELECT * FROM comanda_table ORDER BY IdCliente DESC")
    fun getAllcomanda(): LiveData<List<Comanda>>


}