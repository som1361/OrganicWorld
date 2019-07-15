package com.example.organicworld.model.Service

import com.example.organicworld.model.Entity.OrganicDetailDTO
import com.example.organicworld.model.Entity.OrganicSearchDTO
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OrganicService {

    @GET("search")
    fun searchOrganic(@Query("key") key: String, @Query("q") q: String ) : Single<OrganicSearchDTO.Organics>

    @GET ("get")
    fun getOrganic(@Query("key") key: String, @Query("rId") rId: String) : Single<OrganicDetailDTO.Result>

    companion object {
        fun create(): OrganicService {
            val retrofit = Retrofit.Builder().addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://www.food2fork.com/api/")
                .build()
            return retrofit.create(OrganicService::class.java)
        }
    }
}