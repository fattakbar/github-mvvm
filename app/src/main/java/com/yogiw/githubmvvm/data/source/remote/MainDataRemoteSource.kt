package com.yogiw.githubmvvm.data.source.remote

import android.util.Log
import com.yogiw.githubmvvm.api.ApiService
import com.yogiw.githubmvvm.data.MainData
import com.yogiw.githubmvvm.data.source.MainDataSource
import com.yogiw.githubmvvm.util.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MainDataRemoteSource : MainDataSource {

    private val apiService = ApiService.create()

    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        apiService.getMainData(Constant.username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    run {

                        if (it.name != ""){
                            val mainData = MainData(it.name,
                                    it.location,
                                    it.email,
                                    it.company,
                                    it.avatar_url,
                                    "${it.followers.toString()} \nFollowers",
                                    "${it.following.toString()} \nFollowings",
                                    "${it.public_repos.toString()} \nRepos"
                            )
                            callback.onDataLoaded(mainData)
                        } else {
                            callback.onNotAvailable()
                        }

                    }
                }, {
                    callback.onError(it.message )
                })
    }
}