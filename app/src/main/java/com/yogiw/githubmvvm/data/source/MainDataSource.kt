package com.yogiw.githubmvvm.data.source

import com.yogiw.githubmvvm.data.MainData

interface MainDataSource {
    fun getMainData(callback: GetMainDataCallback)

    interface GetMainDataCallback{
        fun onDataLoaded(mainData: MainData?)
        fun onNotAvailable()
        fun onError(msg: String?)
    }
}