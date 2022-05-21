package com.zerdasoftware.nutrientsbook.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zerdasoftware.nutrientsbook.model.Nutrient

@Dao
interface NutrientDAO {

    //Data Access Object
    @Insert
    suspend fun insertAll(vararg nutrient: Nutrient) : List<Long>

    //Insert -> Room, insert into
    //suspend -> coroutine scope
    //vararg -> birden fazla ve istediğimiz sayıda Nutrient objesini
    //List<Long> -> long döndürüyor, id'ler

    @Query("SELECT * FROM Nutrient ")
    suspend fun getAllNutrient() : List<Nutrient>

    @Query("SELECT * FROM Nutrient WHERE uuid = :nutrientID")
    suspend fun getNutrient(nutrientID:Int) : Nutrient


    @Query("DELETE FROM Nutrient ")
    suspend fun deleteALLNutrient()

}