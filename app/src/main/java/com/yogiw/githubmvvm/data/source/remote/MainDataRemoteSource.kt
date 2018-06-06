package com.yogiw.githubmvvm.data.source.remote

import com.yogiw.githubmvvm.api.ApiService
import com.yogiw.githubmvvm.data.MainData
import com.yogiw.githubmvvm.data.source.MainDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MainDataRemoteSource : MainDataSource {

    private val apiService = ApiService.create()

    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        apiService.getMainData("yogiwisesa")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    run {
                        if (it != null){
                            val mainData = MainData(
                                    it.name,
                                    it.location,
                                    it.email.toString(),
                                    it.company.toString(),
                                    it.avatar_url,
                                    it.followers,
                                    it.following,
                                    it.public_repos
                            )
                            callback.onDataLoaded(mainData)
                        } else {
                            callback.onNotAvailable()
                        }

                    }
                }, {
                    callback.onError(it.message)
                })
    }
}