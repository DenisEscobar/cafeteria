package com.example.mykotlinapplication.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "platosfav")
data class platofav(
    @PrimaryKey(autoGenerate = true)
    var Idfav: Long = 0L,
    @ColumnInfo(name = "nomPlato")
    var NomPlato: String? = null,
    @ColumnInfo(name = "nomUsuari")
    var NomUsuari: String? = null,
    )
