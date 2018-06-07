package com.yogiw.githubmvvm.data.source

import com.yogiw.githubmvvm.data.MainData
import com.yogiw.githubmvvm.data.source.local.MainDataLocalSource


// REPOSITORY SATU AJA
class MainDataRepository(
        val remoteDataSource: MainDataSource,
        val localDataSource: MainDataSource
) : MainDataSource{
    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        remoteDataSource.getMainData(object : MainDataSource.GetMainDataCallback{
            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

            override fun onDataLoaded(mainData: MainData?) {
                callback.onDataLoaded(mainData)
            }
        })
    }

    companion object {
        private var INSTANCE: MainDataRepository? = null

        @JvmStatic
        fun getInstance(mainDataRemoteSource: MainDataSource, newsLocalDataSource: MainDataLocalSource) =
               INSTANCE ?: synchronized(MainDataRepository::class.java) {
                   INSTANCE?: MainDataRepository(mainDataRemoteSource, mainDataRemoteSource)
                           .also { INSTANCE = it }

        }

        @JvmStatic
        fun destroyInstance(){
            INSTANCE = null
        }
    }

}