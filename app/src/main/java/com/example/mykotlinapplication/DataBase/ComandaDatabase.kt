package com.example.mykotlinapplication.DataBase

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mykotlinapplication.RoomViewModel
import com.example.mykotlinapplication.RoomViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Comanda::class, log::class, platos::class, platofav::class], version = 1, exportSchema = false)
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
                        "Coffe_database"
                    )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread(Runnable { prepopulate(getInstance(context)) }).start()
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
        private fun prepopulate(db: ComandaDatabase) {
            GlobalScope.launch {
                val plat=platos()

                plat.NomPlato="coca-cola"
                plat.PrecioPlato="2.5"
                plat.DescripcioPlato="cola"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)
                
                plat.NomPlato="fanta limon"
                plat.PrecioPlato="2"
                plat.DescripcioPlato="limonada"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)
                
                plat.NomPlato="fanta naranga"
                plat.PrecioPlato="2"
                plat.DescripcioPlato="narangada"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="pepsi"
                plat.PrecioPlato="2.5"
                plat.DescripcioPlato="pepsi"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="ristretto"
                plat.PrecioPlato="3.5"
                plat.DescripcioPlato="ristretto"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="amstel"
                plat.PrecioPlato="2.5"
                plat.DescripcioPlato="amstel"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="paulaner"
                plat.PrecioPlato="2.8"
                plat.DescripcioPlato="paulaner"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="san migel"
                plat.PrecioPlato="2.9"
                plat.DescripcioPlato="san-migel"
                plat.CategoriaPlato="beguda"
                db.comandaDatabaseDao.insertplat(plat)


                plat.NomPlato="bocadillo fuet"
                plat.PrecioPlato="2.5"
                plat.DescripcioPlato="fuet"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="bocadillo tortilla"
                plat.PrecioPlato="4"
                plat.DescripcioPlato="tortilla"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="bocadillo salami"
                plat.PrecioPlato="3"
                plat.DescripcioPlato="salami"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="bocadillo atun"
                plat.PrecioPlato="1.5"
                plat.DescripcioPlato="atun"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="bocadillo chorizo"
                plat.PrecioPlato="2"
                plat.DescripcioPlato="chorizo"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="bocadillo jamon"
                plat.PrecioPlato="3.5"
                plat.DescripcioPlato="jamon"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="bocadillo morzilla"
                plat.PrecioPlato="4.5"
                plat.DescripcioPlato="morzilla"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="bocadillo jamon i queso"
                plat.PrecioPlato="3"
                plat.DescripcioPlato="jamon con queso"
                plat.CategoriaPlato="entrepan"
                db.comandaDatabaseDao.insertplat(plat)


                plat.NomPlato="cafe"
                plat.PrecioPlato="1.5"
                plat.DescripcioPlato="cafe"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)


                plat.NomPlato="flan"
                plat.PrecioPlato="1"
                plat.DescripcioPlato="flan"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="pastel de manzana"
                plat.PrecioPlato="3"
                plat.DescripcioPlato="manzana"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="pastel de vainilla"
                plat.PrecioPlato="5"
                plat.DescripcioPlato="vainilla"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="pastel de chocolate"
                plat.PrecioPlato="4"
                plat.DescripcioPlato="chocolate"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="helado de chocolate"
                plat.PrecioPlato="1.2"
                plat.DescripcioPlato="chocolate"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="helado de vainilla"
                plat.PrecioPlato="2.5"
                plat.DescripcioPlato="vainilla"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

                plat.NomPlato="helado de limon"
                plat.PrecioPlato="2.5"
                plat.DescripcioPlato="limon"
                plat.CategoriaPlato="postre"
                db.comandaDatabaseDao.insertplat(plat)

            }
        }
    }
}
