package com.gk.unitconverterapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gk.unitconverterapp.constant.Constants.DATABASE_NAME

@Database(entities = [ConversionResult::class], version = 1, exportSchema = false)
abstract class ConverterDatabase : RoomDatabase() {
    abstract fun getConvertDAO(): ConverterDAO

    companion object {
        @Volatile
        private var INSTANCE: ConverterDatabase? = null
        fun getInstance(context: Context): ConverterDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context = context,
                        klass = ConverterDatabase::class.java,
                        name = DATABASE_NAME
                    ).build()
                }
                return instance
            }
        }
    }
}