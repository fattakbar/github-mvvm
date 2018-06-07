package com.yogiw.githubmvvm.api

import com.yogiw.githubmvvm.api.dao.MainDataDao
import com.yogiw.githubmvvm.util.Constant
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    fun getMainData(
            @Path("username") username: String
    ): Observable<MainDataDao>

    companion object Factory {

        fun create():ApiService{

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}