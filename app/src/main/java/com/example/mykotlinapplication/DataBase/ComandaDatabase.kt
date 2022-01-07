package com.example.mykotlinapplication.DataBase

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mykotlinapplication.RoomViewModel
import com.example.mykotlinapplication.RoomViewModelFactory

@Database(entities = [Comanda::class, log::class, platos::class], version = 4, exportSchema = false)
abstract class ComandaDatabase : RoomDatabase() {
    abstract val comandaDatabaseDao: ComandaDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ComandaDatabase? = null

        fun getInstance(context: Context): ComandaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ComandaDatabase::class.java,
                        "sleep_history_database"
                    )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread(Runnable { prepopulateDb(getInstance(context)) }).start()
                        }
                    })
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
        private fun prepopulateDb(db: ComandaDatabase) {
            GlobalScope.launch {
                val plat=platos()
                plat.NomPlato="coca-cola"
                plat.PrecioPlato="2.5"
                plat.DescripcioPlato="cola"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)
                
                plat.NomPlato="fanta llimona"
                plat.PrecioPlato="2"
                plat.DescripcioPlato="llimona"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)
                
                plat.NomPlato="fanta taronga"
                plat.PrecioPlato="2"
                plat.DescripcioPlato="taronga"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="entrepan fuet"
                plat.PrecioPlato="3"
                plat.DescripcioPlato="fuet"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="cafe"
                plat.PrecioPlato="1.5"
                plat.DescripcioPlato="cafe"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)
            }
        }
    }
}
