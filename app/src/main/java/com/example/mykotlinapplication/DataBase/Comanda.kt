package com.example.mykotlinapplication.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "comanda_table")
data class Comanda(
    @PrimaryKey(autoGenerate = true)
    var IdComanda: Long = 0L,
    @ColumnInfo(name = "nom_client")
    var nomclient: String? = null,
    @ColumnInfo(name = "primer_plato")
    var primerplato: String? = null,
    @ColumnInfo(name = "segundo_plato")
    var segundoplato: String? = null,
    @ColumnInfo(name = "tercer_plato")
    var tercerplato: String? = null,
    @ColumnInfo(name = "precio_total")
    var preciototal: String? = null,
)
