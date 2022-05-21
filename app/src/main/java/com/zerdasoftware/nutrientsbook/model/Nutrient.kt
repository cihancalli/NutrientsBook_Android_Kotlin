package com.zerdasoftware.nutrientsbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Nutrient(
    @ColumnInfo(name = "Title")
    @SerializedName("isim")
    val nutrientTitle:String?,

    @ColumnInfo(name = "Calorie")
    @SerializedName("kalori")
    val nutrientCalorie:String?,

    @ColumnInfo(name = "Carbohydrate")
    @SerializedName("karbonhidrat")
    val nutrientCarbohydrate:String?,

    @ColumnInfo(name = "Protein")
    @SerializedName("protein")
    val nutrientProtein:String?,

    @ColumnInfo(name = "Fat")
    @SerializedName("yag")
    val nutrientFat:String?,

    @ColumnInfo(name = "Link")
    @SerializedName("gorsel")
    val nutrientImage:String?,
) {

    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}