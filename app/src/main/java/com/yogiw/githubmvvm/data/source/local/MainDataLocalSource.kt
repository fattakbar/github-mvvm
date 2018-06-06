package com.yogiw.githubmvvm.data.source.local

import android.content.SharedPreferences
import android.support.annotation.VisibleForTesting
import com.yogiw.githubmvvm.data.source.MainDataSource
import java.util.prefs.AbstractPreferences

class MainDataLocalSource  private constructor(private val preferences: SharedPreferences) : MainDataSource{
    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {

    }

    companion object {
        private var INSTANCE: MainDataLocalSource? = null
        @JvmStatic
        fun getInstance(preferences: SharedPreferences) : MainDataLocalSource?{
            if (INSTANCE == null){
                synchronized(MainDataLocalSource::class.java){
                    INSTANCE = MainDataLocalSource(preferences)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance(){
            INSTANCE = null
        }
    }
}