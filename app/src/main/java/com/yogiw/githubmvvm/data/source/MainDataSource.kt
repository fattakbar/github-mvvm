package com.yogiw.githubmvvm.data.source

import com.yogiw.githubmvvm.data.MainData
import com.yogiw.githubmvvm.data.RepoData

interface MainDataSource {
    fun getMainData(callback: GetMainDataCallback)
    fun getRepoData(callback: GetRepoDataCallback)

    interface GetMainDataCallback{
        fun onDataLoaded(mainData: MainData?)
        fun onNotAvailable()
        fun onError(msg: String?)
    }

    interface GetRepoDataCallback{
        fun onDataLoaded(repoData: MutableList<RepoData?>)
        fun onNotAvailable()
        fun onError(msg: String?)
    }

    // NANTI INTERFACENYA DITAMBAHIN MISALKAN GETSUBDATA
}