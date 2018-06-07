package com.yogiw.githubmvvm.data.source.remote

import android.util.Log
import com.yogiw.githubmvvm.api.ApiService
import com.yogiw.githubmvvm.api.dao.RepoDataDao
import com.yogiw.githubmvvm.data.MainData
import com.yogiw.githubmvvm.data.RepoData
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

                        if (it.name != "") {
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
                    callback.onError(it.message)
                })
    }

    override fun getRepoData(callback: MainDataSource.GetRepoDataCallback) {
        apiService.getReposData(Constant.username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    run {

                        if (it.isNotEmpty()) {
                            Log.i("xx", " ${it.size}")

                            val listRepo: MutableList<RepoData?> = mutableListOf<RepoData?>()
                            for (item: RepoDataDao in it) {
                                Log.i("xx", " -- ${item.description}")
                                val repoData = RepoData(
                                        item.name,
                                        item.language,
                                        item.description,
                                        item.html_url
                                )
                                listRepo.add(repoData)


                            }

                            callback.onDataLoaded(listRepo)
                        } else {
                            callback.onNotAvailable()
                        }

                    }
                }, {
                    callback.onError(it.message)
                })
    }

}