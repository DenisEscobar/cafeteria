package com.example.mykotlinapplication.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "platosfav")
data class platofav(
    @ColumnInfo(name = "nomPlato")
    var NomPlato: String? = null,
    @ColumnInfo(name = "nomUsuari")
    var DescripcioPlato: String? = null,
    @ColumnInfo(name = "fav")
    var PrecioPlato: String? = null,
    )
