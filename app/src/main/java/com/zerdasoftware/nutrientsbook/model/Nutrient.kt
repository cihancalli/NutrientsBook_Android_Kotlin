package com.zerdasoftware.nutrientsbook.model

import com.google.gson.annotations.SerializedName

data class Nutrient(
    @SerializedName("isim")
    val nutrientTitle:String?,
    @SerializedName("kalori")
    val nutrientCalorie:String?,
    @SerializedName("karbonhidrat")
    val nutrientCarbohydrate:String?,
    @SerializedName("protein")
    val nutrientProtein:String?,
    @SerializedName("yag")
    val nutrientFat:String?,
    @SerializedName("gorsel")
    val nutrientImage:String?,
) {
}