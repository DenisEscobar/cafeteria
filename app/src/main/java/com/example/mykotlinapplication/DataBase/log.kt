package com.example.mykotlinapplication.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class log(
@PrimaryKey(autoGenerate = true)
var IdUsuario: Long = 0L,
@ColumnInfo(name = "usuari")
var Usuari: String? = null,
@ColumnInfo(name = "password")
var Password: String? = null,
@ColumnInfo(name = "email")
var Email: String? = null,

)