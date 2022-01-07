package com.example.mykotlinapplication.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "platos")
data class platos(
    @PrimaryKey(autoGenerate = true)
    var IdPlatos: Long = 0L,
    @ColumnInfo(name = "nom")
    var NomPlato: String? = null,
    @ColumnInfo(name = "desctipcio")
    var DescripcioPlato: String? = null,
    @ColumnInfo(name = "preu")
    var PrecioPlato: String? = null,
    @ColumnInfo(name = "categoria")
    var CategoriaPlato: String? = null,
)