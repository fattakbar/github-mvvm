package com.yogiw.githubmvvm.util

import android.content.Context
import android.preference.PreferenceManager
import com.yogiw.githubmvvm.data.source.MainDataRepository
import com.yogiw.githubmvvm.data.source.local.MainDataLocalSource
import com.yogiw.githubmvvm.data.source.remote.MainDataRemoteSource

object Injection {
    fun providedMainDataRepository(context: Context) = MainDataRepository.getInstance(MainDataRemoteSource,
            MainDataLocalSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context))!!)
}