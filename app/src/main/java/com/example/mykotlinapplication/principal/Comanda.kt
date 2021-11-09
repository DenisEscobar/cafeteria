package com.example.mykotlinapplication.principal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "comanda_table")
data class Comanda(
    @PrimaryKey(autoGenerate = true)
    var IdCliente: Long = 0L,
    @ColumnInfo(name = "primer_plato")
    val primerplato: String? = null,
    @ColumnInfo(name = "segundo_plato")
    var segundoplato: String? = null,
    @ColumnInfo(name = "tercer_plato")
    var tercerplato: String? = null,

)
