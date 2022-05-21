package com.zerdasoftware.nutrientsbook.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class PrivateSharedPreferences {

    companion object {

        private val timeString = "time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : PrivateSharedPreferences? = null

        private val lock = Any()
        operator fun invoke(context: Context) : PrivateSharedPreferences = instance ?: synchronized(lock) {
            instance ?: PrivateSharedPreferences(context).also {
                instance = it
            }
        }

        private fun createPrivateSharedPreferences(context: Context):PrivateSharedPreferences {
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return PrivateSharedPreferences()
        }
    }

    fun saveTime(timeData:Long){
        sharedPreferences?.edit(commit = true){
            putLong(timeString,timeData)
        }
    }
}