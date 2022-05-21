package com.zerdasoftware.nutrientsbook.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zerdasoftware.nutrientsbook.model.Nutrient

@Database(entities = arrayOf(Nutrient::class), version = 1)
abstract class NutrientDatabase :RoomDatabase() {
    abstract fun nutrientDAO() : NutrientDAO

    //Singleton

    companion object {

        @Volatile private var instance : NutrientDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NutrientDatabase::class.java,"nutrientdatabase").build()
    }


}